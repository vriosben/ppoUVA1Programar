package modelo;

import java.util.UUID;

public class Inscripcion {
    private final UUID id;
    private UUID eventoId; 
    private UUID asistenteId;
    private transient Evento evento; // transient para evitar recursión
    private transient Asistente asistente;
    private boolean presente;
    private int calificacion; 
    private String comentario;

    public Inscripcion(Evento evento, Asistente asistente) {
        this.id = UUID.randomUUID();
        this.evento = evento;
        this.asistente = asistente;
        this.eventoId = evento.getId();
        this.asistenteId = asistente.getId();
        this.presente = false;
        this.calificacion = 0;
        this.comentario = "";
    }
    
    public Inscripcion(UUID id, UUID eventoId, UUID asistenteId, boolean presente, int calificacion, String comentario) {
        this.id = id;
        this.eventoId = eventoId;
        this.asistenteId = asistenteId;
        this.presente = presente;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }


    // Getters 
    public UUID getId() { return id; }
    public Evento getEvento() { return evento; }
    public Asistente getAsistente() { return asistente; }
    public UUID getEventoId() { return eventoId; }
    public UUID getAsistenteId() { return asistenteId; }
    public int getCalificacion() { return calificacion; }
    public String getComentario() { return comentario; }

   // Setters
    public void setEvento(Evento evento) { this.evento = evento; }
    public void setAsistente(Asistente asistente) { this.asistente = asistente; }
    public void setPresente(boolean presente) { this.presente = presente; }
    public void setCalificacion(int calificacion) { this.calificacion = calificacion; }
    public void setComentario(String comentario) { this.comentario = comentario; }


    // Auxiliares
   public boolean isPresente() { return presente; }
}