package UVA6.src;

public class Libro {
    private String titulo;
    private String autor;
    private String categoria;
    private String isbn;
    private boolean disponible;
    
    // Constructor
    public Libro(String titulo, String autor, String categoria, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.isbn = isbn;
        this.disponible = true;
    }
    
    // Getters
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getCategoria() { return categoria; }
    public String getIsbn() { return isbn; }
    public boolean isDisponible() { return disponible; }
    
    // Setters
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}