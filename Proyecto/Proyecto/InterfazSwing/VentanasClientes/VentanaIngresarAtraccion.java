package VentanasClientes;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import Atracciones.Atraccion;
import Modelo.Parque;
import PanelesClientes.NotificationsListener;
import PanelesClientes.PanelDetalllesTiquetes;
import PanelesClientes.PanelListaAtraccion;
import PanelesClientes.PanelListaTiquetes;
import PanelesClientes.PanelOpciones;
import Tiquetes.Tiquete;
import Usuarios.Usuario;

public class VentanaIngresarAtraccion extends JFrame implements NotificationsListener{
	private VentanaParque ventanaPrincipal;
	private PanelOpciones opciones;
	private PanelListaAtraccion atracciones;
	private PanelListaTiquetes tiquetes;
	private PanelDetalllesTiquetes detalles;
	private Parque parque;
	private Usuario usuario;
	
	public VentanaIngresarAtraccion(VentanaParque ventanaPrincipal, Parque parque, Usuario usuario) {
		this.parque=parque;
		this.ventanaPrincipal=ventanaPrincipal;
		this.usuario=usuario;
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		opciones= new PanelOpciones("Ingresar", ventanaPrincipal, this);
		atracciones= new PanelListaAtraccion(parque);
		detalles= new PanelDetalllesTiquetes( );
		tiquetes = new PanelListaTiquetes(usuario, detalles);
		
		opciones.setAlignmentX(Component.CENTER_ALIGNMENT);
		tiquetes.setAlignmentX(Component.CENTER_ALIGNMENT);
		atracciones.setAlignmentX(Component.CENTER_ALIGNMENT);
		detalles.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(atracciones);
		add(tiquetes);
		add(detalles);
		add(opciones);
		
		setTitle("Escoja la atracción y el tiquete con el que desea entrar");
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	cerrarVentana();
            }
        });
		
		actualizarDatos();
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
	}

	@Override
	public void accionDesdePanel() {
		Atraccion atraccion= atracciones.getAtraccion();
		Tiquete tiquete=tiquetes.getTiquete();
		if (tiquete!= null && atraccion!=null) {
			try {
        		parque.IngresarAtraccion(atraccion, tiquete);
        		if (usuario.getFastPassPorUsar().size()>0) {
        			System.out.print("\nEl cliente pudo ingresar con FastPass a la atracción\n");
        		}
        	}
        	catch (Exception e) {
        		System.out.print("\n"+e.getMessage()+"\n");
        	}
			cerrarVentana();
		}
		else {
			System.out.print("\nPor favor escoger el tiquete y la atracción que desee\n");
		}
		
	}
	
	private void actualizarDatos() {
		tiquetes.actualizarTiquetes();
		atracciones.actualizarAtracciones();
		cambiarTiqueteSeleccionado(usuario.getTiquetesPorUsar().get(0));
		cambiarAtraccionSeleccionada(parque.getAtracciones().get(0));
	}
	
	public void cambiarTiqueteSeleccionado(Tiquete tiquete) {
		tiquetes.seleccionarTiquete(tiquete);
	}
	
	public void cambiarAtraccionSeleccionada(Atraccion atraccion) {
		atracciones.seleccionarAtraccion(atraccion);
	}
	
	private void cerrarVentana() {
		ventanaPrincipal.setVisible(true);
		dispose();
	}
}
