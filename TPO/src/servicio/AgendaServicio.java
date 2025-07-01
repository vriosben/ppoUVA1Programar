package servicio;

import modelo.Asistente;
import modelo.Evento;
import modelo.Inscripcion;
import util.AppException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AgendaServicio {

    private PersistenciaServicio persistencia;
    private List<Evento> eventos;
    private List<Asistente> asistentes;
    private List<Inscripcion> inscripciones;

    public AgendaServicio() {
        this.persistencia = new PersistenciaServicio();
        // Carga inicial de datos desde los archivos
        this.eventos = persistencia.cargarEventos();
        this.asistentes = persistencia.cargarAsistentes();
        this.inscripciones = persistencia.cargarInscripciones();
        
        // Una vez cargados los datos, se reconstruyen las relaciones entre los objetos en memoria.
        reconstruirRelaciones();
    }
    

    // Reconstruye las relaciones, asigno Evento - Asistentes e inscripciones a cada evento.

    private void reconstruirRelaciones() {

        for (Inscripcion i : inscripciones) {
            i.setEvento(eventos.stream().filter(e -> e.getId().equals(i.getEventoId())).findFirst().orElse(null));
            i.setAsistente(asistentes.stream().filter(a -> a.getId().equals(i.getAsistenteId())).findFirst().orElse(null));
        }
        

        for (Evento e : eventos) {
            e.setInscripciones(
                inscripciones.stream()
                             .filter(i -> i.getEventoId() != null && i.getEventoId().equals(e.getId()))
                             .collect(Collectors.toList())
            );
        }
    }
    
    // Procesa inscripciones de un evento para encontrar asistentes
    private void sincronizarNuevosAsistentes(Evento evento) {
        if (evento.getInscripciones() == null) return;

        for (Inscripcion inscripcion : evento.getInscripciones()) {
            boolean asistenteExiste = asistentes.stream()
                .anyMatch(a -> a.getId().equals(inscripcion.getAsistente().getId()));
            if (!asistenteExiste) {
                asistentes.add(inscripcion.getAsistente());
            }

            boolean inscripcionExiste = inscripciones.stream()
                .anyMatch(i -> i.getId().equals(inscripcion.getId()));
            if (!inscripcionExiste) {
                inscripciones.add(inscripcion);
            }
        }
    }
    
    // Crea un nuevo evento
    public void crearEvento(Evento evento) throws AppException {
        sincronizarNuevosAsistentes(evento);
        this.eventos.add(evento);
        
        persistencia.guardarEventos(this.eventos);
        persistencia.guardarAsistentes(this.asistentes);
        persistencia.guardarInscripciones(this.inscripciones);
    }
    
    // Actualizo eventos
    public void actualizarEvento(Evento evento) throws AppException {
        sincronizarNuevosAsistentes(evento);

        persistencia.guardarEventos(this.eventos);
        persistencia.guardarAsistentes(this.asistentes);
        persistencia.guardarInscripciones(this.inscripciones);
    }

    // Eliminar Eventos
    public void eliminarEvento(UUID idEvento) throws AppException {

        boolean removedFromList = this.eventos.removeIf(evento -> evento.getId().equals(idEvento));
        if (removedFromList) {
            // También elimina las inscripciones asociadas a este evento
            this.inscripciones.removeIf(inscripcion -> inscripcion.getEventoId().equals(idEvento));

            // Guarda los cambios en los archivos de persistencia
            persistencia.guardarEventos(this.eventos);
            persistencia.guardarInscripciones(this.inscripciones);
            // No es necesario guardar asistentes a menos que la eliminación de un evento afecte directamente a los asistentes de alguna manera no cubierta por la eliminación de inscripciones
            System.out.println("Evento con ID " + idEvento + " eliminado exitosamente.");
        } else {
            System.out.println("Evento con ID " + idEvento + " no encontrado para eliminar.");
        }

    }

    public List<Evento> getEventosDelDia(LocalDate fecha) {
        return eventos.stream()
                .filter(e -> e.getFechaInicio().toLocalDate().equals(fecha))
                .collect(Collectors.toList());
    }

    public List<Evento> buscarEventosFinalizados(UUID id, LocalDate desde, LocalDate hasta) {
        Stream<Evento> stream = eventos.stream()
            .filter(e -> e.getFechaFin().isBefore(LocalDateTime.now())); 

        if (id != null) {
            stream = stream.filter(e -> e.getId().equals(id));
        }
        if (desde != null) {
            stream = stream.filter(e -> !e.getFechaInicio().toLocalDate().isBefore(desde));
        }
        if (hasta != null) {
            stream = stream.filter(e -> !e.getFechaFin().toLocalDate().isAfter(hasta));
        }
        return stream.collect(Collectors.toList());
    }

    public void actualizarInscripcion(Inscripcion inscripcion) {
        try {
            persistencia.guardarInscripciones(this.inscripciones);
        } catch (AppException e) {
            e.printStackTrace();
        }
    }
    
    public boolean existeEvento(UUID id) {
        return eventos.stream().anyMatch(e -> e.getId().equals(id));
    }
}