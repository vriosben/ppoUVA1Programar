package UVA6.src;

import java.time.LocalDate;

public class Prestamo {
    private String idLibro;
    private String idUsuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    
    public Prestamo(String idLibro, String idUsuario) {
        this.idLibro = idLibro;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null;
    }
    
    // Getters y setters
    public String getIdLibro() { return idLibro; }
    public String getIdUsuario() { return idUsuario; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    
    public void setFechaDevolucion(LocalDate fecha) { this.fechaDevolucion = fecha; }
    
    @Override
    public String toString() {
        return idLibro + "," + idUsuario + "," + fechaPrestamo + "," + 
               (fechaDevolucion != null ? fechaDevolucion : "null");
    }
}