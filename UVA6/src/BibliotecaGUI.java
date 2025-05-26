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
        // Implementación similar para prestar libros
    }
    
    private void mostrarDevolverLibro() {
        // Implementación similar para devolver libros
    }
    
    private void mostrarAdminLibros() {
        // Implementación para administración de libros
    }
}