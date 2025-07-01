package servicio;

import modelo.*;
import util.AppException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersistenciaServicio {

    private static final String EVENTOS_FILE = "recursos/eventos.txt";
    private static final String ASISTENTES_FILE = "recursos/asistentes.txt";
    private static final String INSCRIPCIONES_FILE = "recursos/inscripciones.txt";
    private static final String DELIMITER = ";";

    // Métodos para Eventos
    public List<Evento> cargarEventos() {
        List<Evento> eventos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(EVENTOS_FILE))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(DELIMITER);
                if (partes.length == 7) {
                    UUID id = UUID.fromString(partes[0]);
                    String titulo = partes[1];
                    String ubicacion = partes[2];
                    LocalDateTime fechaInicio = LocalDateTime.parse(partes[3]);
                    LocalDateTime fechaFin = LocalDateTime.parse(partes[4]);
                    String descripcion = partes[5];
                    boolean finalizado = Boolean.parseBoolean(partes[6]);
                    eventos.add(new Evento(id, titulo, ubicacion, fechaInicio, fechaFin, descripcion, finalizado));
                }
            }
        } catch (IOException e) {
            System.err.println("No se encontró el archivo de eventos, se creará uno nuevo.");
        }
        return eventos;
    }

    public void guardarEventos(List<Evento> eventos) throws AppException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EVENTOS_FILE))) {
            for (Evento evento : eventos) {
                String linea = String.join(DELIMITER,
                        evento.getId().toString(),
                        evento.getTitulo(),
                        evento.getUbicacion(),
                        evento.getFechaInicio().toString(),
                        evento.getFechaFin().toString(),
                        evento.getDescripcion(),
                        String.valueOf(evento.isFinalizado()));
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new AppException("Error al guardar los eventos en el archivo.", e);
        }
    }

    // Métodos para Asistentes
    public List<Asistente> cargarAsistentes() {
        List<Asistente> asistentes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ASISTENTES_FILE))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(DELIMITER);
                if (partes.length == 4) {
                    UUID id = UUID.fromString(partes[0]);
                    String nombre = partes[1];
                    String apellido = partes[2];
                    String email = partes[3];
                    asistentes.add(new Asistente(id, nombre, apellido, email));
                }
            }
        } catch (IOException e) {
            System.err.println("No se encontró el archivo de asistentes, se creará uno nuevo.");
        }
        return asistentes;
    }

    public void guardarAsistentes(List<Asistente> asistentes) throws AppException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ASISTENTES_FILE))) {
            for (Asistente asistente : asistentes) {
                String linea = String.join(DELIMITER,
                        asistente.getId().toString(),
                        asistente.getNombre(),
                        asistente.getApellido(),
                        asistente.getEmail());
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new AppException("Error al guardar los asistentes en el archivo.", e);
        }
    }
    
    // Métodos para Inscripciones 
    public List<Inscripcion> cargarInscripciones() {
        List<Inscripcion> inscripciones = new ArrayList<>();
         try (BufferedReader reader = new BufferedReader(new FileReader(INSCRIPCIONES_FILE))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(DELIMITER);
                if (partes.length == 6) {
                    UUID id = UUID.fromString(partes[0]);
                    UUID eventoId = UUID.fromString(partes[1]);
                    UUID asistenteId = UUID.fromString(partes[2]);
                    boolean presente = Boolean.parseBoolean(partes[3]);
                    int calificacion = Integer.parseInt(partes[4]);
                    String comentario = partes.length > 5 ? partes[5] : ""; 
                    inscripciones.add(new Inscripcion(id, eventoId, asistenteId, presente, calificacion, comentario));
                }
            }
        } catch (IOException e) {
            System.err.println("No se encontró el archivo de inscripciones, se creará uno nuevo.");
        }
        return inscripciones;
    }

    public void guardarInscripciones(List<Inscripcion> inscripciones) throws AppException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INSCRIPCIONES_FILE))) {
            for (Inscripcion inscripcion : inscripciones) {
                String linea = String.join(DELIMITER,
                        inscripcion.getId().toString(),
                        inscripcion.getEventoId().toString(),
                        inscripcion.getAsistenteId().toString(),
                        String.valueOf(inscripcion.isPresente()),
                        String.valueOf(inscripcion.getCalificacion()),
                        inscripcion.getComentario());
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new AppException("Error al guardar las inscripciones en el archivo.", e);
        }
    }
}