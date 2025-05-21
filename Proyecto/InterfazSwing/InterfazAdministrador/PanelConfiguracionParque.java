package InterfazAdministrador;

import javax.swing.*;
import Modelo.Parque;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelConfiguracionParque extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtNombre;
    private JTextField txtUbicacion;
    private JTextField txtHorario;

    private Parque parque; // 💡 Referencia pasada desde VentanaAdministrador

    public PanelConfiguracionParque(Parque parque) {
        this.parque = parque;

        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Configuración del Parque", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 10, 10));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        panelCampos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Ubicación:"));
        txtUbicacion = new JTextField();
        panelCampos.add(txtUbicacion);

        panelCampos.add(new JLabel("Horario:"));
        txtHorario = new JTextField();
        panelCampos.add(txtHorario);

        add(panelCampos, BorderLayout.CENTER);

        JButton btnGuardar = new JButton("Guardar Cambios");
        add(btnGuardar, BorderLayout.SOUTH);

        cargarDatosParque();

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });
    }

    private void cargarDatosParque() {
        txtNombre.setText(parque.getNombre());
        txtUbicacion.setText(parque.getUbicacion());
        txtHorario.setText(parque.getHorario());
    }

    private void guardarCambios() {
        parque.setNombre(txtNombre.getText());
        parque.setUbicacion(txtUbicacion.getText());
        parque.setHorario(txtHorario.getText());

        JOptionPane.showMessageDialog(this, "¡Configuración guardada con éxito!");
    }
}
