package UVA6.src;

public class Usuario {
    private String id;
    private String nombre;
    
    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    
    @Override
    public String toString() {
        return id + "," + nombre;
    }
}