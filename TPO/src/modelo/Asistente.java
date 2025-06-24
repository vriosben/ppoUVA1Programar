package modelo;

import java.util.UUID;

public class Asistente {
    private final UUID id;
    private String nombre;
    private String apellido;
    private String email;

    public Asistente(String nombre, String apellido, String email) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }
    
    public Asistente(UUID id, String nombre, String apellido, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    // Getters 
    public UUID getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getApellido() { return apellido; }
    
    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
