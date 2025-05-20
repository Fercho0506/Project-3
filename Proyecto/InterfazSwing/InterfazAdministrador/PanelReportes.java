package InterfazAdministrador;

import javax.swing.*;

import Modelo.Parque;

import java.awt.*;

public class PanelReportes extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Parque parque;
    private JTextArea areaReportes;

    public PanelReportes() {
        this.parque = Parque.getInstance();

        setLayout(new BorderLayout());
        areaReportes = new JTextArea();
        areaReportes.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaReportes);

        JButton btnGenerar = new JButton("Generar Reporte General");
        btnGenerar.addActionListener(_ -> generarReporte());

        add(new JLabel("Reportes del Parque", SwingConstants.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btnGenerar, BorderLayout.SOUTH);
    }

    private void generarReporte() {
        // Aquí puedes personalizar qué datos mostrar; esto es un ejemplo básico.
        StringBuilder reporte = new StringBuilder();
        reporte.append("--- Reporte General del Parque ---\n\n");
        reporte.append("Total de atracciones: ").append(parque.getAtracciones().size()).append("\n");
        reporte.append("Total de empleados: ").append(parque.getEmpleados().size()).append("\n");
        reporte.append("Tiquetes vendidos: ").append(parque.getTotalTiquetesVendidos()).append("\n");
        reporte.append("Ingresos generados: $").append(parque.getIngresosTotales()).append("\n");

        areaReportes.setText(reporte.toString());
    }
}
