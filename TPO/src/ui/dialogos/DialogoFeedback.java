package ui.dialogos;

import modelo.Inscripcion; 
import javax.swing.*;
import java.awt.*;

public class DialogoFeedback extends JDialog { 
    private Inscripcion inscripcion;
    private JComboBox<Integer> comboCalificacion;
    private JTextArea txtComentario;
    private boolean guardado = false;

    public DialogoFeedback(Frame owner, Inscripcion inscripcion) {
        super(owner, "Feedback del Evento", true);
        this.inscripcion = inscripcion;

        setSize(400, 300);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelCentral.add(new JLabel("Calificación (1-5):"), gbc);
        gbc.gridx = 1;
        comboCalificacion = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        if(inscripcion.getCalificacion() > 0) {
            comboCalificacion.setSelectedItem(inscripcion.getCalificacion());
        }
        panelCentral.add(comboCalificacion, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        panelCentral.add(new JLabel("Comentario (opcional):"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        txtComentario = new JTextArea(5, 20);
        txtComentario.setText(inscripcion.getComentario());
        panelCentral.add(new JScrollPane(txtComentario), gbc);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnCancelar);
        panelBotones.add(btnGuardar);

        add(panelCentral, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
    
    private void guardar() {
        inscripcion.setCalificacion((Integer) comboCalificacion.getSelectedItem());
        inscripcion.setComentario(txtComentario.getText());
        this.guardado = true;
        dispose();
    }
    
    public boolean isGuardado() {
        return guardado;
    }
}