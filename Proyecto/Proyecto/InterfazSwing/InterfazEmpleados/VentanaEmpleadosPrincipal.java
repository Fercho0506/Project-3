package InterfazEmpleados;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import InterfazPrincipal.VentanaPrincipal;
import Modelo.Parque;
import Usuarios.Empleado;
import VentanasClientes.VentanaClientesPrincipal;

public class VentanaEmpleadosPrincipal extends JFrame{
	private Parque parque;
	private Empleado empleado;
	private VentanaClientesPrincipal ventanaClientes;
	private PanelEmpleados panel;
	private VentanaPrincipal ventanaPrincipal;
	//private VentanaPassword password;
	
	public  VentanaEmpleadosPrincipal(Parque parque, Empleado empleado, VentanaPrincipal ventanaPrincipal) {
		this.parque=parque;
		this.empleado=empleado;
		this.ventanaPrincipal=ventanaPrincipal;
		
		setLayout( new BorderLayout( ) );
		panel= new PanelEmpleados(this);
		
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(panel, BorderLayout.CENTER);
		
		setTitle("PANTALLA EMPLEADOS");
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	cerrarVentana();
            }
        });
		
		actualizarEmpleado();
		
		pack();
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
	}
	
	public void mostrarClientes() {
		if (ventanaClientes==null || !ventanaClientes.isVisible()) {
			ventanaClientes= new VentanaClientesPrincipal(parque, empleado, this);
			ventanaClientes.setVisible(true);
			this.setVisible(false);
		}
	}
	
	private void actualizarEmpleado() {
		panel.actualizarEmpleado(empleado);
	}
	
	public void cerrarVentana() {
		ventanaPrincipal.setVisible(true);
		dispose();
	}
}
