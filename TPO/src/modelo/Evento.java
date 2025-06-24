package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Evento {
    private final UUID id;
    private String titulo;
    private String ubicacion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String descripcion;
    private boolean finalizado;
    private transient List<Inscripcion> inscripciones; // uso transient acá para que no se genere un bucle en la información y que solo coloque los datos simples. 
                                                    // Al cargar la app, se reconstruye en memoria (método reconstruirRelaciones)

    public Evento(String titulo, String ubicacion, LocalDateTime fechaInicio, LocalDateTime fechaFin, String descripcion) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.ubicacion = ubicacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
        this.finalizado = false;
        this.inscripciones = new ArrayList<>();
    }
    
     public Evento(UUID id, String titulo, String ubicacion, LocalDateTime fechaInicio, LocalDateTime fechaFin, String descripcion, boolean finalizado) {
        this.id = id;
        this.titulo = titulo;
        this.ubicacion = ubicacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
        this.finalizado = finalizado;
        this.inscripciones = new ArrayList<>();
    }

    // Getters
    public UUID getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getUbicacion() { return ubicacion; }
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public LocalDateTime getFechaFin() { return fechaFin; }
    public String getDescripcion() { return descripcion; }
    public List<Inscripcion> getInscripciones() { return inscripciones; }

    // Setters
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }
    public void setFechaFin(LocalDateTime fechaFin) { this.fechaFin = fechaFin; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setFinalizado(boolean finalizado) { this.finalizado = finalizado; }
    public void setInscripciones(List<Inscripcion> inscripciones) { this.inscripciones = inscripciones; }

    // Auxiliares
    public boolean isFinalizado() { return finalizado; }

    public void agregarInscripcion(Inscripcion inscripcion) {
        this.inscripciones.add(inscripcion);
    }
}