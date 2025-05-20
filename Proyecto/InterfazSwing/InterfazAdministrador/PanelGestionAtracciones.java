package InterfazAdministrador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Atracciones.Atraccion;
import Modelo.Parque;

import java.awt.*;
import java.util.List;

public class PanelGestionAtracciones extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaAtracciones;
    private DefaultTableModel modeloTabla;
    private Parque parque;

    public PanelGestionAtracciones() {
        this.parque = Parque.getInstance(); // Suponiendo patrón Singleton

        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Tipo", "Capacidad"}, 0);
        tablaAtracciones = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaAtracciones);

        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");

        btnAgregar.addActionListener(_ -> agregarAtraccion());
        btnEditar.addActionListener(_ -> editarAtraccion());
        btnEliminar.addActionListener(_ -> eliminarAtraccion());

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        add(new JLabel("Gestión de Atracciones", SwingConstants.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        cargarAtracciones();
    }

    private void cargarAtracciones() {
        modeloTabla.setRowCount(0);
        List<Atraccion> atracciones = parque.getAtracciones();
        for (Atraccion atr : atracciones) {
            modeloTabla.addRow(new Object[]{atr.getNombre(), atr.getTipo(), atr.getCapacidad()});
        }
    }

    private void agregarAtraccion() {
        // Puedes reemplazar esto con un diálogo personalizado más adelante
        String nombre = JOptionPane.showInputDialog("Nombre de la atracción:");
        String tipo = JOptionPane.showInputDialog("Tipo:");
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Capacidad:"));

        parque.crearAtraccion(nombre, tipo, capacidad); // Método a implementar si no existe
        cargarAtracciones();
    }

    private void editarAtraccion() {
        int fila = tablaAtracciones.getSelectedRow();
        if (fila != -1) {
            String nombre = (String) modeloTabla.getValueAt(fila, 0);
            Atraccion atr = parque.buscarAtraccionPorNombre(nombre); // Método auxiliar

            String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:", atr.getNombre());
            String nuevoTipo = JOptionPane.showInputDialog("Nuevo tipo:", atr.getTipo());
            int nuevaCapacidad = Integer.parseInt(JOptionPane.showInputDialog("Nueva capacidad:", atr.getCapacidad()));

            atr.setNombre(nuevoNombre);
            atr.setTipo(nuevoTipo);
            atr.setCapacidad(nuevaCapacidad);

            cargarAtracciones();
        }
    }

    private void eliminarAtraccion() {
        int fila = tablaAtracciones.getSelectedRow();
        if (fila != -1) {
            String nombre = (String) modeloTabla.getValueAt(fila, 0);
            parque.eliminarAtraccion(nombre); // Método auxiliar necesario
            cargarAtracciones();
        }
    }
}
