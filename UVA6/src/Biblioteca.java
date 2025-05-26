package UVA6.src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Libro> libros;
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;
    
    public Biblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
        prestamos = new ArrayList<>();
        cargarDatos();
    }
    
    private void cargarDatos() {
        cargarLibros();
        cargarUsuarios();
        cargarPrestamos();
    }

    // Métodos para Usuarios
    private void cargarUsuarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/usuarios.dat"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                Usuario usuario = new Usuario(datos[0], datos[1]);
                usuarios.add(usuario);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de usuarios no encontrado. Se creará uno nuevo al guardar.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de usuarios.");
        }
    }

    public void guardarUsuarios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/usuarios.dat"))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios.");
        }
    }

    // Métodos para guardar/cargar libros (ya implementados)
    private void cargarLibros() {
        // Implementación existente
    }

    public void guardarLibros() {
        // Implementación existente
    }

    public List<Libro> buscarLibrosPorTitulo(String titulo) {
    List<Libro> resultados = new ArrayList<>();
    for (Libro libro : libros) {
        if (libro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
            resultados.add(libro);
        }
    }
    return resultados;
}

    // Métodos para guardar/cargar préstamos
    private void cargarPrestamos() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/prestamos.dat"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                Prestamo prestamo = new Prestamo(datos[0], datos[1]);
                if (!datos[3].equals("null")) {
                    prestamo.setFechaDevolucion(java.time.LocalDate.parse(datos[3]));
                }
                prestamos.add(prestamo);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de préstamos no encontrado. Se creará uno nuevo al guardar.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de préstamos.");
        }
    }

    public void guardarPrestamos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/prestamos.dat"))) {
            for (Prestamo prestamo : prestamos) {
                writer.write(prestamo.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar préstamos.");
        }
    }

    // Método para guardar todos los datos
    public void guardarDatos() {
        guardarLibros();
        guardarUsuarios();
        guardarPrestamos();
    }


}