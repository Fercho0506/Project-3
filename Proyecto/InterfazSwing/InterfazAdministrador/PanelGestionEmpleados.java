package InterfazAdministrador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Modelo.Parque;
import Usuarios.Empleado;

import java.awt.*;
import java.util.List;

public class PanelGestionEmpleados extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaEmpleados;
    private DefaultTableModel modeloTabla;
    private Parque parque;

    public PanelGestionEmpleados() {
        this.parque = Parque.getInstance();

        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Rol", "ID"}, 0);
        tablaEmpleados = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);

        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");

        btnAgregar.addActionListener(_ -> agregarEmpleado());
        btnEditar.addActionListener(_ -> editarEmpleado());
        btnEliminar.addActionListener(_ -> eliminarEmpleado());

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        add(new JLabel("Gestión de Empleados", SwingConstants.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        cargarEmpleados();
    }

    private void cargarEmpleados() {
        modeloTabla.setRowCount(0);
        List<Empleado> empleados = parque.getEmpleados();
        for (Empleado emp : empleados) {
            modeloTabla.addRow(new Object[]{emp.getNombre(), emp.getRol(), emp.getId()});
        }
    }

    private void agregarEmpleado() {
        String nombre = JOptionPane.showInputDialog("Nombre del empleado:");
        String rol = JOptionPane.showInputDialog("Rol del empleado:");
        String id = JOptionPane.showInputDialog("ID del empleado:");

        parque.crearEmpleado(nombre, rol, id); // Método a implementar
        cargarEmpleados();
    }

    private void editarEmpleado() {
        int fila = tablaEmpleados.getSelectedRow();
        if (fila != -1) {
            String id = (String) modeloTabla.getValueAt(fila, 2);
            Empleado emp = parque.buscarEmpleadoPorId(id); // Método auxiliar

            String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:", emp.getNombre());
            String nuevoRol = JOptionPane.showInputDialog("Nuevo rol:", emp.getRol());

            emp.setNombre(nuevoNombre);
            emp.setRol(nuevoRol);

            cargarEmpleados();
        }
    }

    private void eliminarEmpleado() {
        int fila = tablaEmpleados.getSelectedRow();
        if (fila != -1) {
            String id = (String) modeloTabla.getValueAt(fila, 2);
            parque.eliminarEmpleado(id); // Método a implementar
            cargarEmpleados();
        }
    }
}
