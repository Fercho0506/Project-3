package InterfazAdministrador;

import javax.swing.*;

import Modelo.Parque;

import java.awt.*;

public class PanelReportes extends JPanel {

    private static final long serialVersionUID = 1L;
    private Parque parque;
    private JTextArea areaReportes;

    public PanelReportes() {
        this.parque = Parque.getInstance();

        setLayout(new BorderLayout());

        // Título
        JLabel lblTitulo = new JLabel("Reportes del Parque", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        // Área de texto para reportes
        areaReportes = new JTextArea();
        areaReportes.setEditable(false);
        areaReportes.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(areaReportes);
        add(scrollPane, BorderLayout.CENTER);

        // Botón para generar
        JButton btnGenerar = new JButton("Generar Reporte General");
        btnGenerar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnGenerar.addActionListener(_ -> generarReporte());
        add(btnGenerar, BorderLayout.SOUTH);
    }

    private void generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("--- Reporte General del Parque ---\n\n");
        reporte.append("Total de atracciones: ").append(parque.getAtracciones().size()).append("\n");
        reporte.append("Total de empleados: ").append(parque.getEmpleados().size()).append("\n");
        reporte.append("Tiquetes vendidos: ").append(parque.getTotalTiquetesVendidos()).append("\n");
        reporte.append("Ingresos generados: $").append(parque.getIngresosTotales()).append("\n");

        areaReportes.setText(reporte.toString());
    }
}
