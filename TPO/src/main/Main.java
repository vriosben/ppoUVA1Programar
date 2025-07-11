package main;

import ui.MainFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Main {
    public static void main(String[] args) {
        // SwingUtilities.invokeLater asegura que todo el código de la GUI se ejecute 
        // en el Event Dispatch Thread (EDT), lo cual es una práctica recomendada
        // para evitar problemas de concurrencia en Swing.
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                // Si Nimbus no está disponible o falla, la aplicación no se detiene.
                // Simplemente imprimirá el error y continuará con el L&F por defecto.
                System.err.println("El Look and Feel 'Nimbus' no se pudo cargar. Se usará el L&F por defecto.");
                e.printStackTrace();
            }
            
            // Crea una instancia de la ventana principal y la hace visible.
            // Esto inicia la interfaz de la aplicación.
            new MainFrame().setVisible(true);
        });
    }
}