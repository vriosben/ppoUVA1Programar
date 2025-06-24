package ui.paneles; 

import ui.MainFrame;
import javax.swing.*;
import java.awt.*;

public class ReporteriaPanel extends JPanel {
    public ReporteriaPanel(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        add(new JLabel("Vista de Reportería (En Desarrollo)", SwingConstants.CENTER), BorderLayout.CENTER);
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> mainFrame.cambiarVista("MENU"));
        add(btnVolver, BorderLayout.SOUTH);
    }
}
