package biblioteca;
import java.util.Comparator;

// Comparador por año de publicación
public class ComparadorAnio implements Comparator<Libro> {
    @Override
    public int compare(Libro l1, Libro l2) {
        return Integer.compare(l1.getAnioPublicacion(), l2.getAnioPublicacion());
    }
}
