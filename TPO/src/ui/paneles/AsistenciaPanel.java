package ui.paneles; 

import modelo.Evento; 
import modelo.Inscripcion; 
import servicio.AgendaServicio;
import ui.MainFrame;
import ui.dialogos.DialogoFeedback; 

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

public class AsistenciaPanel extends JPanel {
    private MainFrame mainFrame;
    private AgendaServicio agendaServicio;

    // Componentes de Búsqueda
    private JTextField txtIdBusqueda;
    private JTextField txtFechaDesde;
    private JTextField txtFechaHasta;
    private JList<Evento> listaResultados;
    private DefaultListModel<Evento> listModel;

    // Componentes de Detalles y Asistentes
    private JPanel panelAsistentes;
    private JLabel lblTituloEvento, lblUbicacionEvento, lblFechasEvento;


    public AsistenciaPanel(MainFrame mainFrame, AgendaServicio agendaServicio) {
        this.mainFrame = mainFrame;
        this.agendaServicio = agendaServicio;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 1. PANEL DE BÚSQUEDA 
        JPanel panelBusqueda = new JPanel(new GridBagLayout());
        panelBusqueda.setBorder(new TitledBorder("Buscar Eventos Finalizados"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; panelBusqueda.add(new JLabel("ID Evento:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; txtIdBusqueda = new JTextField(20); panelBusqueda.add(txtIdBusqueda, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; panelBusqueda.add(new JLabel("Desde (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; txtFechaDesde = new JTextField(10); panelBusqueda.add(txtFechaDesde, gbc);
        
        gbc.gridx = 2; gbc.gridy = 1; panelBusqueda.add(new JLabel("Hasta (YYYY-MM-DD):"), gbc);
        gbc.gridx = 3; gbc.gridy = 1; txtFechaHasta = new JTextField(10); panelBusqueda.add(txtFechaHasta, gbc);

        JPanel panelBotonesBusqueda = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnBuscar = new JButton("Buscar");
        JButton btnReset = new JButton("Reset");
        panelBotonesBusqueda.add(btnBuscar);
        panelBotonesBusqueda.add(btnReset);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4; panelBusqueda.add(panelBotonesBusqueda, gbc);
        add(panelBusqueda, BorderLayout.NORTH);

        // 2. PANEL CENTRAL (Resultados y Detalles)
        JSplitPane splitPaneCentral = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneCentral.setResizeWeight(0.3);

        listModel = new DefaultListModel<>();
        listaResultados = new JList<>(listModel);
        listaResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaResultados.setCellRenderer(new EventoListCellRenderer());
        JScrollPane scrollResultados = new JScrollPane(listaResultados);
        scrollResultados.setBorder(new TitledBorder("Resultados de Búsqueda"));
        splitPaneCentral.setLeftComponent(scrollResultados);

        JPanel panelDerecho = new JPanel(new BorderLayout(5, 5));
        JPanel panelDetalles = new JPanel(new GridLayout(0, 1));
        panelDetalles.setBorder(new TitledBorder("Detalles del Evento (No editable)"));
        lblTituloEvento = new JLabel("Seleccione un evento...");
        lblTituloEvento.setFont(new Font("Arial", Font.BOLD, 16));
        lblUbicacionEvento = new JLabel();
        lblFechasEvento = new JLabel();
        panelDetalles.add(lblTituloEvento);
        panelDetalles.add(lblUbicacionEvento);
        panelDetalles.add(lblFechasEvento);
        
        panelAsistentes = new JPanel();
        panelAsistentes.setLayout(new BoxLayout(panelAsistentes, BoxLayout.Y_AXIS));
        JScrollPane scrollAsistentes = new JScrollPane(panelAsistentes);
        scrollAsistentes.setBorder(new TitledBorder("Asistentes"));

        panelDerecho.add(panelDetalles, BorderLayout.NORTH);
        panelDerecho.add(scrollAsistentes, BorderLayout.CENTER);
        splitPaneCentral.setRightComponent(panelDerecho);
        add(splitPaneCentral, BorderLayout.CENTER);
        
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> mainFrame.cambiarVista("MENU"));
        panelSur.add(btnVolver);
        add(panelSur, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> buscarEventos());
        btnReset.addActionListener(e -> resetBusqueda());
        listaResultados.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDetallesEvento(listaResultados.getSelectedValue());
            }
        });

        resetBusqueda();
    }
    
    private void buscarEventos() {
        try {
            UUID id = null;
            if (!txtIdBusqueda.getText().trim().isEmpty()) {
                id = UUID.fromString(txtIdBusqueda.getText().trim());
            }
            LocalDate desde = null;
            if (!txtFechaDesde.getText().trim().isEmpty()) {
                desde = LocalDate.parse(txtFechaDesde.getText().trim());
            }
            LocalDate hasta = null;
            if (!txtFechaHasta.getText().trim().isEmpty()) {
                hasta = LocalDate.parse(txtFechaHasta.getText().trim());
            }

            List<Evento> eventos = agendaServicio.buscarEventosFinalizados(id, desde, hasta);
            listModel.clear();
            eventos.forEach(listModel::addElement);

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "El ID del evento no es válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "El formato de fecha es incorrecto. Use uuuu-MM-dd.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetBusqueda() {
        txtIdBusqueda.setText("");
        txtFechaDesde.setText("");
        txtFechaHasta.setText("");
        listModel.clear();
        limpiarDetalles();
    }
    
    private void limpiarDetalles() {
        lblTituloEvento.setText("Seleccione un evento de la lista...");
        lblUbicacionEvento.setText("");
        lblFechasEvento.setText("");
        panelAsistentes.removeAll();
        panelAsistentes.revalidate();
        panelAsistentes.repaint();
    }

    private void mostrarDetallesEvento(Evento evento) {
        if (evento == null) {
            limpiarDetalles();
            return;
        }

        lblTituloEvento.setText(evento.getTitulo());
        lblUbicacionEvento.setText("Ubicación: " + evento.getUbicacion());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        lblFechasEvento.setText(String.format("Inicio: %s - Fin: %s", 
                                evento.getFechaInicio().format(dtf), 
                                evento.getFechaFin().format(dtf)));
        
        panelAsistentes.removeAll();
        List<Inscripcion> inscripciones = evento.getInscripciones();
        if (inscripciones.isEmpty()) {
            panelAsistentes.add(new JLabel("No hay asistentes inscritos para este evento."));
        } else {
            for (Inscripcion inscripcion : inscripciones) {
                panelAsistentes.add(crearPanelAsistente(inscripcion));
            }
        }
        panelAsistentes.revalidate();
        panelAsistentes.repaint();
    }

    private JPanel crearPanelAsistente(Inscripcion inscripcion) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.LIGHT_GRAY));
        
        JLabel nombre = new JLabel(inscripcion.getAsistente().getNombre() + " " + inscripcion.getAsistente().getApellido());
        nombre.setPreferredSize(new Dimension(200, 20));
        
        JCheckBox chkPresente = new JCheckBox("Asistió", inscripcion.isPresente());
        
        JButton btnFeedback = new JButton("Feedback");
        btnFeedback.setEnabled(inscripcion.isPresente());

        chkPresente.addActionListener(e -> {
            btnFeedback.setEnabled(chkPresente.isSelected());
            inscripcion.setPresente(chkPresente.isSelected());
            if (!chkPresente.isSelected()) {
                inscripcion.setCalificacion(0);
                inscripcion.setComentario("");
            }
            agendaServicio.actualizarInscripcion(inscripcion);
        });

        btnFeedback.addActionListener(e -> {
            DialogoFeedback dialog = new DialogoFeedback(mainFrame, inscripcion); // MODIFICADO
            dialog.setVisible(true);
            if (dialog.isGuardado()) {
                 agendaServicio.actualizarInscripcion(inscripcion);
                 JOptionPane.showMessageDialog(this, "Feedback guardado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        panel.add(nombre);
        panel.add(chkPresente);
        panel.add(btnFeedback);
        
        return panel;
    }
}

// Clase interna para renderizar los eventos en la JList
class EventoListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Evento) {
            Evento evento = (Evento) value;
            setText(evento.getTitulo());
            setToolTipText(evento.getId().toString());
        }
        return this;
    }
}
