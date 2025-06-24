package ui;

import modelo.Evento; 
import servicio.AgendaServicio;
import ui.paneles.*; 

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Servicios
    private AgendaServicio agendaServicio;

    // Paneles
    private AgendaPanel agendaPanel;
    private EventoPanel eventoPanel;
    private AsistenciaPanel asistenciaPanel; 

    public MainFrame() {
        this.agendaServicio = new AgendaServicio();

        setTitle("Gestor de Eventos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Crear paneles
        MenuPrincipalPanel menuPanel = new MenuPrincipalPanel(this);
        agendaPanel = new AgendaPanel(this, agendaServicio);
        asistenciaPanel = new AsistenciaPanel(this, agendaServicio); 
        ReporteriaPanel reporteriaPanel = new ReporteriaPanel(this);
        eventoPanel = new EventoPanel(this, agendaServicio); 

        // Añadir paneles al CardLayout
        mainPanel.add(menuPanel, "MENU");
        mainPanel.add(agendaPanel, "AGENDA");
        mainPanel.add(asistenciaPanel, "ASISTENCIA"); 
        mainPanel.add(reporteriaPanel, "REPORTERIA");
        mainPanel.add(eventoPanel, "EVENTO_FORM"); 
        
        add(mainPanel);
    }

    public void cambiarVista(String nombreVista) {
        cardLayout.show(mainPanel, nombreVista);
    }
    
    public void mostrarFormularioEvento(Evento evento) {
        eventoPanel.setEvento(evento);
        cambiarVista("EVENTO_FORM");
    }
}