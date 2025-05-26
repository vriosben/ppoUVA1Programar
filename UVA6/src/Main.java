package UVA6.src;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        
        // Registrar un shutdown hook para guardar datos al cerrar la aplicación
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            biblioteca.guardarDatos();
            System.out.println("Datos guardados correctamente.");
        }));
        
        // Iniciar la interfaz gráfica
        SwingUtilities.invokeLater(() -> new BibliotecaGUI(biblioteca));
    }
}