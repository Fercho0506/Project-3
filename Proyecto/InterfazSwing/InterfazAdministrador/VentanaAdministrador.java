package InterfazAdministrador;

import javax.swing.*;
import java.awt.*;

public class VentanaAdministrador extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;

    public VentanaAdministrador() {
        setTitle("Panel de Administrador");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panelPrincipal = new JPanel(new BorderLayout());

        JPanel menuLateral = crearMenuLateral();
        panelPrincipal.add(menuLateral, BorderLayout.WEST);

        // Panel inicial por defecto
        panelPrincipal.add(new JLabel("Bienvenido, Administrador", SwingConstants.CENTER), BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private JPanel crearMenuLateral() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton btnAtracciones = new JButton("Gestionar Atracciones");
        JButton btnEmpleados = new JButton("Gestionar Empleados");
        JButton btnReportes = new JButton("Ver Reportes");
        JButton btnConfiguracion = new JButton("Configuración");
        JButton btnSalir = new JButton("Salir");

        btnAtracciones.addActionListener(_ -> mostrarPanel(new PanelGestionAtracciones()));
        // Los demás botones pueden conectarse leg a sus respectivos paneles

        btnSalir.addActionListener(_ -> dispose());

        panel.add(btnAtracciones);
        panel.add(btnEmpleados);
        panel.add(btnReportes);
        panel.add(btnConfiguracion);
        panel.add(btnSalir);

        return panel;
    }

    private void mostrarPanel(JPanel nuevoPanel) {
        panelPrincipal.remove(1);
        panelPrincipal.add(nuevoPanel, BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaAdministrador().setVisible(true);
        });
    }
}
