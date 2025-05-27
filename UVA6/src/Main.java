package UVA6.src;

import javax.swing.SwingUtilities;

import UVA6.src.biblioteca.Biblioteca;
import UVA6.src.biblioteca.BibliotecaGUI;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        
        // Iniciar la interfaz gráfica
        SwingUtilities.invokeLater(() -> new BibliotecaGUI(biblioteca));
    }
}