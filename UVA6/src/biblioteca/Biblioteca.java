package UVA6.src.biblioteca;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import UVA6.src.excepciones.LibroExistenteException;
import UVA6.src.excepciones.LibroNoDisponibleException;
import UVA6.src.excepciones.LibroNoEncontradoException;
import UVA6.src.excepciones.UsuarioNoEncontradoException;

public class Biblioteca {
    private List<Libro> libros;
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;
    
    // private static final String ARCHIVO_LIBROS = "libros.dat";
    // private static final String ARCHIVO_USUARIOS = "usuarios.dat";
    // private static final String ARCHIVO_PRESTAMOS = "prestamos.dat";
    private static final String ARCHIVO_LIBROS = Paths.get("UVA6", "data", "libros.dat").toString();
    private static final String ARCHIVO_USUARIOS = Paths.get("UVA6", "data", "usuarios.dat").toString();
    private static final String ARCHIVO_PRESTAMOS = Paths.get("UVA6", "data", "prestamos.dat").toString();  
    
    public Biblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
        prestamos = new ArrayList<>();
        cargarDatos();
    }
    
    /* -------------------- Métodos de Persistencia -------------------- */
    
    private void cargarDatos() {
        cargarLibros();
        cargarUsuarios();
        cargarPrestamos();
    }
    
    private void cargarLibros() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_LIBROS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    Libro libro = new Libro(datos[0], datos[1], datos[2], datos[3]);
                    libro.setDisponible(Boolean.parseBoolean(datos[4]));
                    libros.add(libro);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de libros no encontrado. Se creará uno nuevo.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de libros: " + e.getMessage());
        }
    }
    
    private void cargarUsuarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 2) {
                    usuarios.add(new Usuario(datos[0], datos[1]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de usuarios no encontrado. Se creará uno nuevo.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de usuarios: " + e.getMessage());
        }
    }
    
    private void cargarPrestamos() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_PRESTAMOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    Prestamo prestamo = new Prestamo(datos[0], datos[1]);
                    if (!datos[3].equals("null")) {
                        prestamo.setFechaDevolucion(LocalDate.parse(datos[3]));
                    }
                    prestamos.add(prestamo);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de préstamos no encontrado. Se creará uno nuevo.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de préstamos: " + e.getMessage());
        }
    }
    
    public void guardarDatos() {
        guardarLibros();
        guardarUsuarios();
        guardarPrestamos();
    }
    
    private void guardarLibros() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_LIBROS))) {
            for (Libro libro : libros) {
                writer.write(libro.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar libros: " + e.getMessage());
        }
    }
    
    private void guardarUsuarios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_USUARIOS))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }
    
    private void guardarPrestamos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_PRESTAMOS))) {
            for (Prestamo prestamo : prestamos) {
                writer.write(prestamo.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar préstamos: " + e.getMessage());
        }
    }
    
    /* -------------------- Métodos de Gestión de Libros -------------------- */
    
    public void agregarLibro(Libro libro) throws LibroExistenteException {
        if (existeLibro(libro.getIsbn())) {
            throw new LibroExistenteException("El libro con ISBN " + libro.getIsbn() + " ya existe");
        }
        libros.add(libro);
    }
    
    public boolean existeLibro(String isbn) {
        return buscarLibroPorIsbn(isbn) != null;
    }
    
    public Libro buscarLibroPorIsbn(String isbn) {
        for (Libro libro : libros) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
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
    
    public List<Libro> buscarLibrosPorAutor(String autor) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                resultados.add(libro);
            }
        }
        return resultados;
    }
    
    public List<Libro> getTodosLosLibros() {
        return new ArrayList<>(libros);
    }
    
    public boolean eliminarLibro(String isbn) {
        Libro libro = buscarLibroPorIsbn(isbn);
        if (libro != null && libro.isDisponible()) {
            libros.remove(libro);
            return true;
        }
        return false;
    }
    
    /* -------------------- Métodos de Gestión de Usuarios -------------------- */
    
    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
    
    public Usuario buscarUsuario(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }
    
    public List<Usuario> getTodosLosUsuarios() {
        return new ArrayList<>(usuarios);
    }
    
    public boolean existeUsuario(String id) {
        return buscarUsuario(id) != null;
    }
    
    /* -------------------- Métodos de Gestión de Préstamos -------------------- */
    
    public void prestarLibro(String isbn, String usuarioId) throws LibroNoEncontradoException, 
                                                                  LibroNoDisponibleException,
                                                                  UsuarioNoEncontradoException {
        Libro libro = buscarLibroPorIsbn(isbn);
        if (libro == null) {
            throw new LibroNoEncontradoException("Libro no encontrado");
        }
        if (!libro.isDisponible()) {
            throw new LibroNoDisponibleException("El libro no está disponible");
        }
        if (buscarUsuario(usuarioId) == null) {
            throw new UsuarioNoEncontradoException("Usuario no registrado");
        }
        
        libro.setDisponible(false);
        prestamos.add(new Prestamo(isbn, usuarioId));
    }
    
    public void devolverLibro(String isbn) throws LibroNoEncontradoException {
        Libro libro = buscarLibroPorIsbn(isbn);
        if (libro == null) {
            throw new LibroNoEncontradoException("Libro no encontrado");
        }
        
        libro.setDisponible(true);
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getIdLibro().equals(isbn) && prestamo.getFechaDevolucion() == null) {
                prestamo.setFechaDevolucion(LocalDate.now());
                break;
            }
        }
    }
    
    public List<Prestamo> getPrestamosActivos() {
        List<Prestamo> activos = new ArrayList<>();
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getFechaDevolucion() == null) {
                activos.add(prestamo);
            }
        }
        return activos;
    }
    
    public List<Prestamo> getHistorialPrestamos() {
        return new ArrayList<>(prestamos);
    }
}