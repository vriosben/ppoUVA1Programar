package biblioteca;
public class Libro implements Comparable<Libro> {
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String isbn;

    public Libro(String titulo, String autor, int anioPublicacion, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.isbn = isbn;
    }

    // Getters
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnioPublicacion() { return anioPublicacion; }
    public String getIsbn() { return isbn; }

    // Comparación por defecto
    @Override
    public int compareTo(Libro otro) {
        return this.isbn.compareTo(otro.getIsbn());
    }

    // Mostrar información
    @Override
    public String toString() {
    return " \"" + titulo + "\", AUTOR: " + autor + " (" + anioPublicacion + ") - ISBN: " + isbn;
    }

    // Reconocer dos libros iguales
    @Override
    public boolean equals(Object o) {
        Libro otro = (Libro) o;
        return this.isbn.equalsIgnoreCase(otro.getIsbn());
    } 

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

}