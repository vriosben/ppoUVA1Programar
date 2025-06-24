package ui.paneles; 

import modelo.Evento; 
import servicio.AgendaServicio;
import ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AgendaPanel extends JPanel {
    private MainFrame mainFrame;
    private AgendaServicio agendaServicio;
    private JLabel lblFecha;
    private JPanel panelHorario;
    private LocalDate fechaActual;

    public AgendaPanel(MainFrame mainFrame, AgendaServicio agendaServicio) {
        this.mainFrame = mainFrame;
        this.agendaServicio = agendaServicio;
        this.fechaActual = LocalDate.now();
        
        setLayout(new BorderLayout(10, 10));
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lblFecha = new JLabel("", SwingConstants.CENTER);
        lblFecha.setFont(new Font("Arial", Font.BOLD, 18));
        
        JButton btnDiaAnterior = new JButton("<");
        btnDiaAnterior.addActionListener(e -> cambiarDia(-1));
        
        JButton btnDiaSiguiente = new JButton(">");
        btnDiaSiguiente.addActionListener(e -> cambiarDia(1));

        JButton btnCrearEvento = new JButton("Crear Evento");
        btnCrearEvento.addActionListener(e -> mainFrame.mostrarFormularioEvento(null));

        JPanel fechaControlPanel = new JPanel();
        fechaControlPanel.add(btnDiaAnterior);
        fechaControlPanel.add(lblFecha);
        fechaControlPanel.add(btnDiaSiguiente);

        topPanel.add(fechaControlPanel, BorderLayout.CENTER);
        topPanel.add(btnCrearEvento, BorderLayout.EAST);
        
        panelHorario = new JPanel();
        panelHorario.setLayout(new GridLayout(0, 1, 5, 5));
        JScrollPane scrollPane = new JScrollPane(panelHorario);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> mainFrame.cambiarVista("MENU"));
        bottomPanel.add(btnVolver);
        
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                actualizarVistaAgenda();
            }
        });
        
        actualizarVistaAgenda();
    }

    private void cambiarDia(int dias) {
        fechaActual = fechaActual.plusDays(dias);
        actualizarVistaAgenda();
    }
    
    private void actualizarVistaAgenda() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' uuuu");
        lblFecha.setText(fechaActual.format(formatter));
        
        panelHorario.removeAll();
        
        List<Evento> eventosDelDia = agendaServicio.getEventosDelDia(fechaActual);
        
        for (int i = 0; i < 24; i++) {
            JPanel horaPanel = new JPanel(new BorderLayout(5, 5));
            horaPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
            
            JLabel horaLabel = new JLabel(String.format("%02d:00", i), SwingConstants.CENTER);
            horaLabel.setPreferredSize(new Dimension(60, 20));
            horaPanel.add(horaLabel, BorderLayout.WEST);
            
            JPanel eventosEnHoraPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
            final int hora = i;
            eventosDelDia.stream()
                         .filter(e -> e.getFechaInicio().getHour() == hora)
                         .forEach(evento -> {
                             JButton btnEvento = new JButton(evento.getTitulo());
                             btnEvento.setToolTipText(evento.getUbicacion());
                             btnEvento.addActionListener(e -> mainFrame.mostrarFormularioEvento(evento));
                             eventosEnHoraPanel.add(btnEvento);
                         });
            horaPanel.add(eventosEnHoraPanel, BorderLayout.CENTER);
            panelHorario.add(horaPanel);
        }
        
        panelHorario.revalidate();
        panelHorario.repaint();
    }
}