package InterfazEmpleados;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import LugarServicios.Cafeteria;
import Modelo.Parque;
import Usuarios.Cajero;
import Usuarios.Cocinero;
import Usuarios.Empleado;
import Usuarios.EmpleadoAtracciones;
import Usuarios.EmpleadoServiciosgenerales;

public class PanelEmpleados extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String CLIENTES = "clientes";
    private static final String SALIR = "salir";
	private JLabel info;
	private JPanel turnos;
	private JLabel labor;
	private JLabel lugarTrabajo;
	private JButton abrirClientes;
	//private JButton password;
	private JButton salir;
	private Parque ventanaPrincipal;
	
	public PanelEmpleados( Parque parque) {
		this.ventanaPrincipal=parque;
		setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));
		
		info= new JLabel("Mi info");
		turnos= new JPanel();
		turnos.setLayout(new BoxLayout(turnos, BoxLayout.Y_AXIS));
		
		labor= new JLabel("Mi labor");
		lugarTrabajo= new JLabel("Mi lugar de trabajo");
		
		abrirClientes= new JButton("Ir al menú de clientes");
		abrirClientes.setActionCommand(CLIENTES); 
		abrirClientes.addActionListener(this); 
        
		//password= new JButton("Cambiar contraseña");
		//password.setActionCommand(PASSWORD); 
		//password.addActionListener(this); 
        
		salir= new JButton("Volver al menú principal");
		salir.setActionCommand(SALIR); 
		salir.addActionListener(this); 
		
		info.setAlignmentX(Component.CENTER_ALIGNMENT);
        turnos.setAlignmentX(Component.CENTER_ALIGNMENT); 
        labor.setAlignmentX(Component.CENTER_ALIGNMENT);
        lugarTrabajo.setAlignmentX(Component.CENTER_ALIGNMENT); 
        abrirClientes.setAlignmentX(Component.CENTER_ALIGNMENT); 
        //password.setAlignmentX(Component.CENTER_ALIGNMENT);
        salir.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(info);
        add(Box.createVerticalStrut(10));
        add(turnos);
        add(Box.createVerticalStrut(10));
        add(labor);
        add(Box.createVerticalStrut(10));
        add(lugarTrabajo);
        add(Box.createVerticalStrut(10));
        add(abrirClientes);
        //add(password);
        add(salir);
	}
	
	public void actualizarEmpleado(Empleado empleado) {
		actualizarInfo(empleado);
		actualizarTurnos(empleado);
		actualizarLabor(empleado);
		actualizarLugar(empleado);
	}
	
	private void actualizarInfo(Empleado empleado) {
		info.setText("Usted es un empleado: "+empleado.gettipo());
	}

	private void actualizarTurnos(Empleado empleado) {
		JLabel texto;
		if(empleado.gettipo().equals("serviciosGenerales")) {
			texto= new JLabel("Usted como empleado de servicios generales no tiene turnos asignados");
			texto.setAlignmentX(Component.CENTER_ALIGNMENT);
			turnos.add(texto);
		}
		else if (empleado.getTurnos().size()>0) {
			texto= new JLabel("Sus turnos asignados:");
			texto.setAlignmentX(Component.CENTER_ALIGNMENT);
			turnos.add(texto);
			for (String i: empleado.getTurnos()) {
				JLabel turnoLabel = new JLabel("Tiene el turno asignado de: " + i);
	            turnoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	            turnos.add(turnoLabel);
			}
		}
		else {
			texto= new JLabel("En este momento no tiene turnos asignados");
			texto.setAlignmentX(Component.CENTER_ALIGNMENT);
			turnos.add(texto);
		}
	}
	
	private void actualizarLabor(Empleado empleado) {
		if (empleado.getLabor() != "") {
			labor.setText("Su labor del momento es: "+empleado.getLabor());
		}
		else {
			labor.setText("En este momento no una labor asignada");
		}
	}
	
	private void actualizarLugar(Empleado empleado) {
		boolean tienelugar=true;
		if (empleado.gettipo().equals("serviciosGenerales")) {
			EmpleadoServiciosgenerales empleadoServi= (EmpleadoServiciosgenerales) empleado;
			if (empleadoServi.getLugar()!=null) {
				lugarTrabajo.setText("Su lugar de trabajo en este momento es en una: "+empleadoServi.getLugar().getTipo()+" "+empleadoServi.getLugar().getNombre());
			}
			else {
				tienelugar=false;
			}
		}
		else if(empleado.gettipo().equals("cocinero")) {
			Cocinero cocinero= (Cocinero) empleado;
			Cafeteria cafeteria=cocinero.getCafeteria();
			if (cafeteria!=null) {
				lugarTrabajo.setText("Su lugar de trabajo en este momento es en la: cafetería"+" "+cafeteria.getNombre());
			}
			else {
				tienelugar=false;
			}
		}
		else if(empleado.gettipo().equals("empleadoAtracciones")) {
			EmpleadoAtracciones empAtract= (EmpleadoAtracciones) empleado;
			if (empAtract.getAtraccion()!=null) {
				lugarTrabajo.setText("Su lugar de trabajo en este momento es en la atraccción: "+empAtract.getAtraccion().getNombre());
			}
			else {
				tienelugar=false;
			}
		}
		else if(empleado.gettipo().equals("cajero")) {
			Cajero cajero= (Cajero) empleado;
			if (cajero.getAtraccion()!=null) {
				lugarTrabajo.setText("Su lugar de trabajo en este momento es en la atraccción: "+cajero.getAtraccion().getNombre());
			}
			else if(cajero.getLugar()!=null) {
				lugarTrabajo.setText("Su lugar de trabajo en este momento es en la: "+ cajero.getLugar().getTipo()+" "+cajero.getLugar().getNombre());
			}
			else {
				tienelugar=false;
			}
		}
		if(!tienelugar) {
			lugarTrabajo.setText("En este momento no tiene ningún lugar de trabajo asignado");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand( );
		if (CLIENTES.equals(comando)) {
			ventanaPrincipal.mostrarClientes();
		}
		//else if (PASSWORD.equals(comando)) {
		//	ventanaPrincipal.mostrarPassword();
		//} 
		else if (SALIR.equals(comando)) {
			ventanaPrincipal.cerrarVentana();
		}
	}
	
	
}
