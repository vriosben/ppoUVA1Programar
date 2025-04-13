package biblioteca;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Biblioteca {
    private List<Libro> libros;

    public Biblioteca() {
        this.libros = new ArrayList<>();
    }
    
    // Getter
    public List<Libro> getTodos() { return libros; }

    // Agregar libros a la colección
    public boolean agregarLibro(Libro libro) {
        if (!libros.contains(libro)) {
            libros.add(libro);
            return true;
        }
        return false;
    }

    // Eliminar libros por isbn
    public boolean eliminarLibro(String isbn) {
        Iterator<Libro> it = libros.iterator();
        while (it.hasNext()) {
            if (it.next().getIsbn().equalsIgnoreCase(isbn)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    // Buscar libro por título
    public Libro buscarPorTitulo(String titulo) {
        Iterator<Libro> it = libros.iterator();
        while (it.hasNext()) {
            Libro l = it.next();
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return l;
            }
        }
        return null;
    }

    // Ordena los libros por título
    public List<Libro> ordenadosPorTitulo() {
        List<Libro> ordenados = new ArrayList<>(libros);
        ordenados.sort(new ComparadorTitulo());
        return ordenados;
    }

    // Ordena los libros por año de publicación
    public List<Libro> ordenadosPorAnio() {
        List<Libro> ordenados = new ArrayList<>(libros);
        ordenados.sort(new ComparadorAnio());
        return ordenados;
    }
}