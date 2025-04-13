import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import biblioteca.Biblioteca;
import biblioteca.Libro;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        boolean salir = false;

        biblioteca.agregarLibro(new Libro("Dailan Kifki", "María Elena Walsh", 1966, "MEW001"));
        biblioteca.agregarLibro(new Libro("El reino del revés", "María Elena Walsh", 1965, "MEW002"));
        biblioteca.agregarLibro(new Libro("Manuelita ¿dónde vas?", "María Elena Walsh", 1960, "MEW003"));
        biblioteca.agregarLibro(new Libro("El ratón más famoso", "Graciela Montes", 1990, "GM001"));
        biblioteca.agregarLibro(new Libro("Historias de los señores Moc y Poc", "Graciela Montes", 1986, "GM002"));
        biblioteca.agregarLibro(new Libro("La sombra de los otros", "Graciela Montes", 1992, "GM003"));

        while (!salir) {
            System.out.println();
            System.out.println(" Menú Biblioteca");
            System.out.println("1. Agregar libro");
            System.out.println("2. Eliminar libro");
            System.out.println("3. Buscar libro por título");
            System.out.println("4. Mostrar todos");
            System.out.println("5. Ordenados por título");
            System.out.println("6. Ordenados por año");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (opcion) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Año de publicación: ");
                    int anio = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    boolean agregado = biblioteca.agregarLibro(new Libro(titulo, autor, anio, isbn));
                    System.out.println(agregado ? "Libro agregado con éxito." : "Este libro ya existe.");
                    break;

                case 2:
                    System.out.print("ISBN del libro a eliminar: ");
                    String isbnEliminar = scanner.nextLine();
                    boolean eliminado = biblioteca.eliminarLibro(isbnEliminar);
                    System.out.println(eliminado ? "Libro eliminado." : "Libro no encontrado.");
                    break;

                case 3:
                    System.out.print("Título a buscar: ");
                    String tituloBuscar = scanner.nextLine();
                    Libro l = biblioteca.buscarPorTitulo(tituloBuscar);
                    System.out.println(l != null ? " Encontrado: " + l : " No encontrado.");
                    break;

                case 4:
                    System.out.println(" Todos los libros:");
                    List<Libro> libros = biblioteca.getTodos();
                    Iterator<Libro> iterador = libros.iterator();
                    while (iterador.hasNext()) {
                        System.out.println(iterador.next());
                    }
                    break;

                case 5:
                    System.out.println(" Libros ordenados por título:");
                    List<Libro> librosPorTitulo = biblioteca.ordenadosPorTitulo();
                    Iterator<Libro> iteradorPorTitulo = librosPorTitulo.iterator();
                    while (iteradorPorTitulo.hasNext()) {
                        System.out.println(iteradorPorTitulo.next());
                    }
                    break;

                case 6:
                    System.out.println(" Libros ordenados por año:");
                    List<Libro> librosPorAnio = biblioteca.ordenadosPorAnio();
                    Iterator<Libro> iteradorPorAnio = librosPorAnio.iterator();
                    while (iteradorPorAnio.hasNext()) {
                        System.out.println(iteradorPorAnio.next());
                    }
                    break;

                case 0:
                    salir = true;
                    System.out.println(" Hasta luego!");
                    break;

                default:
                    System.out.println(" Opción inválida.");
            }
        }

        scanner.close();
    }
}
