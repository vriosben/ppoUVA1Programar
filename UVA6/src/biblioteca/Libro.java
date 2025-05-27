package UVA6.src.biblioteca;

public class Libro {
    // Atributos
    private String titulo;
    private String autor;
    private String categoria;
    private String isbn;
    private boolean disponible;
    
 
    public Libro(String titulo, String autor, String categoria, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.isbn = isbn;
        this.disponible = true; // Por defecto, al crear un libro está disponible
    }
    
    // Getters
    public String getTitulo() {
        return titulo;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public boolean isDisponible() {
        return disponible;
    }
    
    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    @Override
    public String toString() {
        return String.format(
            "Título: %s\nAutor: %s\nCategoría: %s\nISBN: %s\nDisponible: %s\n",
            titulo,
            autor,
            categoria,
            isbn,
            disponible ? "Sí" : "No"
        );
    }
    
    //Método para obtener representación CSV del libro 
    public String toCSV() {
        return String.join(",",
            titulo,
            autor,
            categoria,
            isbn,
            String.valueOf(disponible)
        );
    }
    
    //comparar libros por ISBN
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Libro libro = (Libro) obj;
        return isbn.equals(libro.isbn);
    }
    
    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}