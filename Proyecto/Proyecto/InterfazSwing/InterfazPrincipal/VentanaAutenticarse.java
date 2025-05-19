package InterfazPrincipal;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import InterfazEmpleados.VentanaEmpleadosPrincipal;
import Modelo.Parque;
import Usuarios.Administrador;
import Usuarios.Empleado;
import Usuarios.Usuario;
import VentanasClientes.VentanaClientesPrincipal;
import consola.ConsolaAdministrador;
import consola.ConsolaClientes;
import consola.ConsolaEmpleados;

public class VentanaAutenticarse extends JFrame{
	private PanelAutenticarse opciones;
	 private VentanaPrincipal ventanaPrincipal;
	 private Parque parque;
	 private PanelValidar ingreso;
	 private VentanasClientes.VentanaClientesPrincipal clientes;
	 private VentanaEmpleadosPrincipal empleados;
	 //private VentanaAdministradorPrincipal administrador;
	 
	 public VentanaAutenticarse( VentanaPrincipal principal , Parque parque)
	    {
	        this.ventanaPrincipal = principal;
	        this.parque=parque;
	        setLayout( new BorderLayout( ) );
	        
	        opciones= new PanelAutenticarse();
	        add(opciones, BorderLayout.CENTER);
	        
	        addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	            	cerrarVentana();
	            }
	        });
	        
	        ingreso= new PanelValidar(this);
	        add(ingreso, BorderLayout.SOUTH);
	        setTitle("Ingrese su usuario y contraseña por favor");
	      
	        pack( );
	        setLocationRelativeTo( null );
	        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
	        setResizable( false );
	    }
	 
	 public void autenticarse() {
		 String login= opciones.getUsuario();
		 String password = opciones.getPassword();
		 
		 if (!login.isEmpty() && !password.isEmpty()) {
			 boolean valido= parque.autenticarIngreso(login, password);
			 if (valido) {
				 Usuario usuario= parque.getUsuario(login);
				 if (usuario.getTipoUsuario().equals("cliente")) {
		    			clientes= new VentanaClientesPrincipal(parque, usuario, ventanaPrincipal);
		    			clientes.setVisible(true);
		    		}
		    		else if (usuario.getTipoUsuario().equals("empleado")) {
		    			Empleado empleado= (Empleado) usuario;
		    			empleados = new VentanaEmpleadosPrincipal(parque, empleado, ventanaPrincipal);
		    			empleados.setVisible(true);
		    		}
		    		else{
		    			Administrador administrador= (Administrador) usuario;
		    			//administrador= new VentanaAdministradorPrincipal(parque, administrador, ventanaPrincipal);
		    			//administrador.setVisible(true);
		    		}
				 dispose();
			 }
			 else {
				 System.out.println("\nIngresó su contraseña o login incorrectamente.\n");
				 cerrarVentana();
			 }
		 }
		 else {
			 System.out.println("\nPor favor rellene todos los campos.\n");
		 }
	 }
	 
	 public void cerrarVentana() {
		 ventanaPrincipal.setVisible(true);
		 dispose();
	 }
}
