package UVA6.src;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BibliotecaGUI {
    private Biblioteca biblioteca;
    private JFrame frame;
    
    public BibliotecaGUI(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        inicializarGUI();
    }
    
    private void inicializarGUI() {
        frame = new JFrame("Sistema de Gestión de Biblioteca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JButton btnBuscarLibro = new JButton("Buscar Libro");
        JButton btnPrestarLibro = new JButton("Prestar Libro");
        JButton btnDevolverLibro = new JButton("Devolver Libro");
        JButton btnAdminLibros = new JButton("Administración de Libros");
        
        btnBuscarLibro.addActionListener(e -> mostrarBuscarLibro());
        btnPrestarLibro.addActionListener(e -> mostrarPrestarLibro());
        btnDevolverLibro.addActionListener(e -> mostrarDevolverLibro());
        btnAdminLibros.addActionListener(e -> mostrarAdminLibros());
        
        panel.add(btnBuscarLibro);
        panel.add(btnPrestarLibro);
        panel.add(btnDevolverLibro);
        panel.add(btnAdminLibros);
        
        frame.add(panel);
        frame.setVisible(true);
    }
    
    private void mostrarBuscarLibro() {
        JFrame buscarFrame = new JFrame("Buscar Libro");
        buscarFrame.setSize(500, 300);
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        
        JTextField txtBusqueda = new JTextField();
        JButton btnBuscar = new JButton("Buscar");
        JTextArea txtResultados = new JTextArea();
        txtResultados.setEditable(false);
        
        btnBuscar.addActionListener(e -> {
            String busqueda = txtBusqueda.getText();
            List<Libro> resultados = biblioteca.buscarLibrosPorTitulo(busqueda);
            
            StringBuilder sb = new StringBuilder();
            for (Libro libro : resultados) {
                sb.append(libro.getTitulo()).append(" - ").append(libro.getAutor())
                  .append(" (").append(libro.getCategoria()).append(") ")
                  .append(libro.isDisponible() ? "Disponible" : "Prestado")
                  .append("\n");
            }
            
            txtResultados.setText(sb.toString());
        });
        
        panel.add(new JLabel("Título o parte del título:"));
        panel.add(txtBusqueda);
        panel.add(btnBuscar);
        panel.add(new JScrollPane(txtResultados));
        
        buscarFrame.add(panel);
        buscarFrame.setVisible(true);
    }
    
private void mostrarPrestarLibro() {
    JFrame prestarFrame = new JFrame("Prestar Libro");
    prestarFrame.setSize(500, 300);
    
    JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
    JTextField txtIsbn = new JTextField();
    JTextField txtUsuarioId = new JTextField();
    JButton btnPrestar = new JButton("Realizar Préstamo");
    JTextArea txtResultado = new JTextArea();
    txtResultado.setEditable(false);
    
    btnPrestar.addActionListener(e -> {
        try {
            String isbn = txtIsbn.getText().trim();
            String usuarioId = txtUsuarioId.getText().trim();
            
            if (isbn.isEmpty() || usuarioId.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios");
            }
            
            biblioteca.prestarLibro(isbn, usuarioId);
            txtResultado.setText("Préstamo realizado con éxito!\n"
                + "ISBN: " + isbn + "\n"
                + "Usuario ID: " + usuarioId);
            
        } catch (LibroNoEncontradoException ex) {
            txtResultado.setText("Error: " + ex.getMessage());
        } catch (LibroNoDisponibleException ex) {
            txtResultado.setText("Error: " + ex.getMessage());
        } catch (Exception ex) {
            txtResultado.setText("Error inesperado: " + ex.getMessage());
        }
    });
    
    panel.add(new JLabel("ISBN del Libro:"));
    panel.add(txtIsbn);
    panel.add(new JLabel("ID del Usuario:"));
    panel.add(txtUsuarioId);
    panel.add(btnPrestar);
    panel.add(new JScrollPane(txtResultado));
    
    prestarFrame.add(panel);
    prestarFrame.setVisible(true);
}
    
private void mostrarDevolverLibro() {
    JFrame devolverFrame = new JFrame("Devolver Libro");
    devolverFrame.setSize(500, 300);
    
    JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
    JTextField txtIsbn = new JTextField();
    JButton btnDevolver = new JButton("Realizar Devolución");
    JTextArea txtResultado = new JTextArea();
    txtResultado.setEditable(false);
    
    btnDevolver.addActionListener(e -> {
        try {
            String isbn = txtIsbn.getText().trim();
            
            if (isbn.isEmpty()) {
                throw new IllegalArgumentException("El ISBN es obligatorio");
            }
            
            // Necesitarás implementar este método en la clase Biblioteca
            biblioteca.devolverLibro(isbn);
            txtResultado.setText("Devolución realizada con éxito!\n"
                + "ISBN: " + isbn);
            
        } catch (LibroNoEncontradoException ex) {
            txtResultado.setText("Error: " + ex.getMessage());
        } catch (Exception ex) {
            txtResultado.setText("Error inesperado: " + ex.getMessage());
        }
    });
    
    panel.add(new JLabel("ISBN del Libro:"));
    panel.add(txtIsbn);
    panel.add(btnDevolver);
    panel.add(new JScrollPane(txtResultado));
    
    devolverFrame.add(panel);
    devolverFrame.setVisible(true);
}
private void mostrarAdminLibros() {
    JFrame adminFrame = new JFrame("Administración de Libros");
    adminFrame.setSize(600, 400);
    
    JTabbedPane tabbedPane = new JTabbedPane();
    
    // Pestaña para agregar libros
    JPanel panelAgregar = new JPanel(new GridLayout(6, 2, 10, 10));
    panelAgregar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
    JTextField txtTitulo = new JTextField();
    JTextField txtAutor = new JTextField();
    JTextField txtCategoria = new JTextField();
    JTextField txtIsbn = new JTextField();
    JButton btnAgregar = new JButton("Agregar Libro");
    JTextArea txtResultado = new JTextArea();
    txtResultado.setEditable(false);
    
    btnAgregar.addActionListener(e -> {
        try {
            Libro nuevoLibro = new Libro(
                txtTitulo.getText().trim(),
                txtAutor.getText().trim(),
                txtCategoria.getText().trim(),
                txtIsbn.getText().trim()
            );
            
            biblioteca.agregarLibro(nuevoLibro);
            txtResultado.setText("Libro agregado con éxito!\n" + nuevoLibro);
            
        } catch (LibroExistenteException ex) {
            txtResultado.setText("Error: " + ex.getMessage());
        } catch (Exception ex) {
            txtResultado.setText("Error inesperado: " + ex.getMessage());
        }
    });
    
    panelAgregar.add(new JLabel("Título:"));
    panelAgregar.add(txtTitulo);
    panelAgregar.add(new JLabel("Autor:"));
    panelAgregar.add(txtAutor);
    panelAgregar.add(new JLabel("Categoría:"));
    panelAgregar.add(txtCategoria);
    panelAgregar.add(new JLabel("ISBN:"));
    panelAgregar.add(txtIsbn);
    panelAgregar.add(btnAgregar);
    panelAgregar.add(new JScrollPane(txtResultado));
    
    // Pestaña para listar libros
    JPanel panelListar = new JPanel(new BorderLayout());
    JTextArea txtListado = new JTextArea();
    txtListado.setEditable(false);
    JButton btnActualizar = new JButton("Actualizar Listado");
    
    btnActualizar.addActionListener(e -> {
        StringBuilder sb = new StringBuilder();
        for (Libro libro : biblioteca.getTodosLosLibros()) {
            sb.append(libro).append("\n----------------\n");
        }
        txtListado.setText(sb.toString());
    });
    
    panelListar.add(new JScrollPane(txtListado), BorderLayout.CENTER);
    panelListar.add(btnActualizar, BorderLayout.SOUTH);
    
    tabbedPane.addTab("Agregar Libro", panelAgregar);
    tabbedPane.addTab("Listar Libros", panelListar);
    
    adminFrame.add(tabbedPane);
    adminFrame.setVisible(true);
}
}