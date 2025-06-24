package ui.paneles; 

import ui.MainFrame;
import javax.swing.*;
import java.awt.*;

public class MenuPrincipalPanel extends JPanel {
    private MainFrame mainFrame;

    public MenuPrincipalPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        
        JButton btnAgenda = new JButton("Agenda");
        JButton btnAsistencia = new JButton("Asistencia");
        JButton btnReporteria = new JButton("Reportería");

        Font btnFont = new Font("Arial", Font.PLAIN, 18);
        btnAgenda.setFont(btnFont);
        btnAsistencia.setFont(btnFont);
        btnReporteria.setFont(btnFont);

        btnAgenda.addActionListener(e -> mainFrame.cambiarVista("AGENDA"));
        btnAsistencia.addActionListener(e -> mainFrame.cambiarVista("ASISTENCIA"));
        btnReporteria.addActionListener(e -> mainFrame.cambiarVista("REPORTERIA"));

        gbc.gridy = 0; add(titulo, gbc);
        gbc.gridy = 1; add(btnAgenda, gbc);
        gbc.gridy = 2; add(btnAsistencia, gbc);
        gbc.gridy = 3; add(btnReporteria, gbc);
    }
}