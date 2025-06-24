package ui.paneles;

import modelo.Asistente;
import modelo.Evento;
import modelo.Inscripcion;
import servicio.AgendaServicio;
import ui.MainFrame;
import util.AppException;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class EventoPanel extends JPanel {
    private MainFrame mainFrame;
    private AgendaServicio agendaServicio;
    private Evento eventoActual;

    // Componentes del Formulario de Evento 
    private JTextField txtTitulo;
    private JTextField txtUbicacion;
    private JTextArea txtDescripcion;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JLabel lblTituloPanel;
    
    // Componentes para Asistentes 
    private DefaultListModel<Asistente> asistentesListModel;
    private JList<Asistente> listaAsistentes;
    private JTextField txtNombreAsistente;
    private JTextField txtApellidoAsistente;
    private JTextField txtEmailAsistente;


    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public EventoPanel(MainFrame mainFrame, AgendaServicio agendaServicio) {
        this.mainFrame = mainFrame;
        this.agendaServicio = agendaServicio;
        
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel del Título Principal
        lblTituloPanel = new JLabel("Crear Nuevo Evento", SwingConstants.CENTER);
        lblTituloPanel.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTituloPanel, BorderLayout.NORTH);

        // Panel de Formulario de Evento (Arriba)
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1; txtTitulo = new JTextField(20); formPanel.add(txtTitulo, gbc);
        gbc.gridy = 1; gbc.gridx = 0; formPanel.add(new JLabel("Ubicación:"), gbc);
        gbc.gridx = 1; txtUbicacion = new JTextField(20); formPanel.add(txtUbicacion, gbc);
        gbc.gridy = 2; gbc.gridx = 0; formPanel.add(new JLabel("Fecha Inicio (yyyy-MM-dd HH:mm):"), gbc);
        gbc.gridx = 1; txtFechaInicio = new JTextField(20); formPanel.add(txtFechaInicio, gbc);
        gbc.gridy = 3; gbc.gridx = 0; formPanel.add(new JLabel("Fecha Fin (yyyy-MM-dd HH:mm):"), gbc);
        gbc.gridx = 1; txtFechaFin = new JTextField(20); formPanel.add(txtFechaFin, gbc);
        gbc.gridy = 4; gbc.gridx = 0; gbc.anchor = GridBagConstraints.NORTH; formPanel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.BOTH; gbc.weightx = 1.0; gbc.weighty = 0.5;
        txtDescripcion = new JTextArea(5, 20);
        formPanel.add(new JScrollPane(txtDescripcion), gbc);

        // Panel de Gestión de Asistentes (Abajo)
        JPanel asistentesPanel = new JPanel(new BorderLayout(5, 5));
        asistentesPanel.setBorder(new TitledBorder("Gestión de Asistentes"));
        
        asistentesListModel = new DefaultListModel<>();
        listaAsistentes = new JList<>(asistentesListModel);
        listaAsistentes.setCellRenderer(new AsistenteListCellRenderer());
        asistentesPanel.add(new JScrollPane(listaAsistentes), BorderLayout.CENTER);

        JPanel formAsistentePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcAsistente = new GridBagConstraints();
        gbcAsistente.insets = new Insets(2,2,2,2);
        gbcAsistente.fill = GridBagConstraints.HORIZONTAL;
        gbcAsistente.gridy = 0; gbcAsistente.gridx = 0; formAsistentePanel.add(new JLabel("Nombre:"), gbcAsistente);
        gbcAsistente.gridx = 1; txtNombreAsistente = new JTextField(10); formAsistentePanel.add(txtNombreAsistente, gbcAsistente);
        gbcAsistente.gridy = 1; gbcAsistente.gridx = 0; formAsistentePanel.add(new JLabel("Apellido:"), gbcAsistente);
        gbcAsistente.gridx = 1; txtApellidoAsistente = new JTextField(10); formAsistentePanel.add(txtApellidoAsistente, gbcAsistente);
        gbcAsistente.gridy = 2; gbcAsistente.gridx = 0; formAsistentePanel.add(new JLabel("Email:"), gbcAsistente);
        gbcAsistente.gridx = 1; txtEmailAsistente = new JTextField(10); formAsistentePanel.add(txtEmailAsistente, gbcAsistente);
        
        JButton btnAddAsistente = new JButton("Añadir Asistente");
        gbcAsistente.gridy = 3; gbcAsistente.gridx = 0; gbcAsistente.gridwidth = 2;
        formAsistentePanel.add(btnAddAsistente, gbcAsistente);
        asistentesPanel.add(formAsistentePanel, BorderLayout.EAST);

        // Contenedor principal con SplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, formPanel, asistentesPanel);
        splitPane.setResizeWeight(0.6);
        add(splitPane, BorderLayout.CENTER);

        // Panel de Botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnGuardar = new JButton("Guardar");
        JButton btnVolver = new JButton("Volver a la Agenda");
        buttonPanel.add(btnVolver);
        buttonPanel.add(btnGuardar);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Action Listeners
        btnVolver.addActionListener(e -> mainFrame.cambiarVista("AGENDA"));
        btnGuardar.addActionListener(e -> guardarEvento());
        btnAddAsistente.addActionListener(e -> agregarAsistente());
    }
    
    public void setEvento(Evento evento) {
        if (evento == null) {
            // creo un evento temporal en memoria para gestionar los asistentes
            this.eventoActual = new Evento("", "", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "");
            this.eventoActual.setInscripciones(new ArrayList<>()); 
            lblTituloPanel.setText("Crear Nuevo Evento");
            txtTitulo.setText("");
            txtUbicacion.setText("");
            txtDescripcion.setText("");
            txtFechaInicio.setText(LocalDateTime.now().format(dtf));
            txtFechaFin.setText(LocalDateTime.now().plusHours(1).format(dtf));
        } else {
            // Modo Edición
            this.eventoActual = evento;
            lblTituloPanel.setText("Editar Evento");
            txtTitulo.setText(evento.getTitulo());
            txtUbicacion.setText(evento.getUbicacion());
            txtDescripcion.setText(evento.getDescripcion());
            txtFechaInicio.setText(evento.getFechaInicio().format(dtf));
            txtFechaFin.setText(evento.getFechaFin().format(dtf));
        }
        
        // Cargar la lista de asistentes
        asistentesListModel.clear();
        if (eventoActual.getInscripciones() != null) {
            eventoActual.getInscripciones().forEach(inscripcion -> asistentesListModel.addElement(inscripcion.getAsistente()));
        }
    }

    private void agregarAsistente() {
        String nombre = txtNombreAsistente.getText().trim();
        String apellido = txtApellidoAsistente.getText().trim();
        String email = txtEmailAsistente.getText().trim();

        if(nombre.isEmpty() || apellido.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre, apellido y email son obligatorios para añadir un asistente.", "Datos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Creo los objetos en memoria
        Asistente nuevoAsistente = new Asistente(nombre, apellido, email);
        Inscripcion nuevaInscripcion = new Inscripcion(eventoActual, nuevoAsistente);

        // Lo añado al estado del evento actual y a la UI
        eventoActual.agregarInscripcion(nuevaInscripcion);
        asistentesListModel.addElement(nuevoAsistente);

        // Limpio los campos
        txtNombreAsistente.setText("");
        txtApellidoAsistente.setText("");
        txtEmailAsistente.setText("");
    }

    private void guardarEvento() {
        if (txtTitulo.getText().trim().isEmpty() || txtUbicacion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El título y la ubicación son obligatorios.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            // Actualizar evento actual
            eventoActual.setTitulo(txtTitulo.getText());
            eventoActual.setUbicacion(txtUbicacion.getText());
            eventoActual.setDescripcion(txtDescripcion.getText());
            eventoActual.setFechaInicio(LocalDateTime.parse(txtFechaInicio.getText(), dtf));
            eventoActual.setFechaFin(LocalDateTime.parse(txtFechaFin.getText(), dtf));
            
            
            boolean esNuevo = !agendaServicio.existeEvento(eventoActual.getId());

            if (esNuevo) {
                agendaServicio.crearEvento(eventoActual);
                JOptionPane.showMessageDialog(this, "Evento creado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                agendaServicio.actualizarEvento(eventoActual);
                JOptionPane.showMessageDialog(this, "Evento actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
            
            mainFrame.cambiarVista("AGENDA");

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "El formato de fecha es incorrecto. Use uuuu-MM-dd HH:mm.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (AppException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el evento: " + ex.getMessage(), "Error de Guardado", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Clase interna para renderizar los asistentes en la JList
    private static class AsistenteListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Asistente) {
                Asistente asistente = (Asistente) value;
                setText(asistente.getNombre() + " " + asistente.getApellido() + " (" + asistente.getEmail() + ")");
            }
            return this;
        }
    }
}