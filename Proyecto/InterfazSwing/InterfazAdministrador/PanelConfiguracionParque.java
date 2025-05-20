package InterfazAdministrador;

import javax.swing.*;
import java.awt.*;
import InterfazAdministrador.PanelGestionAtracciones;
import InterfazAdministrador.PanelGestionEmpleados;
import InterfazAdministrador.PanelReportes;
import InterfazPrincipal.PanelAutenticarse;

public class PanelConfiguracionParque extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelContenido;

    public PanelConfiguracionParque() {
        setTitle("Panel de Administrador");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));

        JButton btnGestionAtracciones = new JButton("Gestionar Atracciones");
        JButton btnGestionEmpleados = new JButton("Gestionar Empleados");
        JButton btnVerReportes = new JButton("Ver Reportes");
        JButton btnConfiguracion = new JButton("Configuración del Parque");
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");

        btnGestionAtracciones.addActionListener(e -> mostrarPanel(new PanelGestionAtracciones()));
        btnGestionEmpleados.addActionListener(e -> mostrarPanel(new PanelGestionEmpleados()));
        btnVerReportes.addActionListener(e -> mostrarPanel(new PanelReportes()));
        btnConfiguracion.addActionListener(e -> mostrarPanel(new PanelConfiguracionParque()));
        btnCerrarSesion.addActionListener(e -> cerrarSesion());

        panelMenu.add(btnGestionAtracciones);
        panelMenu.add(btnGestionEmpleados);
        panelMenu.add(btnVerReportes);
        panelMenu.add(btnConfiguracion);
        panelMenu.add(Box.createVerticalStrut(20));
        panelMenu.add(btnCerrarSesion);

        panelContenido = new JPanel(new BorderLayout());
        panelContenido.add(new JLabel("Bienvenido, Administrador", SwingConstants.CENTER), BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelMenu, BorderLayout.WEST);
        getContentPane().add(panelContenido, BorderLayout.CENTER);
    }

    private void mostrarPanel(JPanel nuevoPanel) {
        panelContenido.removeAll();
        panelContenido.add(nuevoPanel, BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    private void cerrarSesion() {
        dispose();
        SwingUtilities.invokeLater(() -> {
            JFrame ventanaLogin = new JFrame("Inicio de Sesión");
            ventanaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaLogin.setSize(400, 300);
            ventanaLogin.setLocationRelativeTo(null);
            ventanaLogin.add(new PanelAutenticarse());
            ventanaLogin.setVisible(true);
        });
    }
} 
