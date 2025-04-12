import java.util.*;

public class Biblioteca {
    private List<Libro> libros;

    public Biblioteca() {
        this.libros = new ArrayList<>();
    }

    public boolean agregarLibro(Libro libro) {
        if (!libros.contains(libro)) {
            libros.add(libro);
            return true;
        }
        return false;
    }

    public boolean eliminarLibro(String isbn) {
        Iterator<Libro> it = libros.iterator();
        while (it.hasNext()) {
            if (it.next().getIsbn().equals(isbn)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public Libro buscarPorTitulo(String titulo) {
        for (Libro l : libros) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return l;
            }
        }
        return null;
    }

    public List<Libro> getTodos() {
        return libros;
    }

    public List<Libro> ordenadosPorTitulo() {
        List<Libro> ordenados = new ArrayList<>(libros);
        ordenados.sort(new ComparadorTitulo());
        return ordenados;
    }

    public List<Libro> ordenadosPorAnio() {
        List<Libro> ordenados = new ArrayList<>(libros);
        ordenados.sort(new ComparadorAnio());
        return ordenados;
    }
}