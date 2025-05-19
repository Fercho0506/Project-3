package VentanasClientes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Modelo.Parque;
import PanelesClientes.NotificationsListener;
import PanelesClientes.PanelComprarTiquetes;
import PanelesClientes.PanelOpciones;
import Tiquetes.FastPass;
import Tiquetes.Tiquete;
import Usuarios.Usuario;

public class VentanaComprarTiquetes extends JFrame implements NotificationsListener{
	private PanelComprarTiquetes panelPrincipal;
	private Parque parque;
	private Usuario usuario;
	private ArrayList<Tiquete> tiquetes;
	private ArrayList<FastPass> fastPass;
	private PanelOpciones opciones;
	private VentanaTiquetesRegular regular;
	private VentanaEntradas entradas;
	private VentanaTiquetesTemporada temporada;
	private VentanaClientesPrincipal ventanaPrincipal;
	
	public VentanaComprarTiquetes(Parque parque, Usuario usuario, VentanaClientesPrincipal ventanaPrincipal) {
		this.parque=parque;
		this.usuario=usuario;
		this.ventanaPrincipal=ventanaPrincipal;
		this.tiquetes= new ArrayList<Tiquete>();
		this.fastPass= new ArrayList<FastPass>();
		setLayout( new BorderLayout( ) );
		
		opciones= new PanelOpciones("Finalizar Compra", ventanaPrincipal, this);
		panelPrincipal= new PanelComprarTiquetes(this, tiquetes, fastPass, this.usuario);
		
		opciones.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelPrincipal.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(panelPrincipal, BorderLayout.CENTER);
		add(opciones, BorderLayout.SOUTH);
		
		setTitle("Menú comprar tiquetes");
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	cerrarVentana();
            }
        });
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
	}
	
	public void mostrarRegular() {
		if (regular == null || !regular.isVisible( )) {
			regular = new VentanaTiquetesRegular(tiquetes, this, usuario);
			regular.setVisible( true );
			this.setVisible(false);
		}
	}
	
	public void mostrarTemporada() {
		if (temporada == null || !temporada.isVisible( )) {
			temporada = new VentanaTiquetesTemporada(tiquetes, this, usuario);
			temporada.setVisible( true );
			this.setVisible(false);
		}
	}
	
	public void mostrarEntrada() {
		if ((entradas == null || !entradas.isVisible( )) && parque.getAtracciones().size()>0) {
			entradas = new VentanaEntradas(this, parque, tiquetes, usuario);
			entradas.setVisible( true );
			this.setVisible(false);
		}
		else {
			System.out.print("\nPor el momento no hay atracciones disponibles para comprar tiquetes\n");
		}
	}
	
	public void actualizarDetalles() {
		panelPrincipal.setDetalles();
	}
	
	public void cerrarVentana() {
		ventanaPrincipal.setVisible(true);
		dispose();
	}

	@Override
	public void accionDesdePanel() {
		if (tiquetes.isEmpty() && fastPass.isEmpty()) {
			System.out.print("No hay tiquetes o FastPass agregados, entonces se cancela la compra\n");
			cerrarVentana();
		}
		else {
			float total= parque.calcularPrecioCompraTiquetes(usuario, tiquetes, fastPass);
			int respuesta =0;
			if (usuario.getTipoUsuario().compareTo("cliente") ==0) {
				respuesta = JOptionPane.showConfirmDialog(
					    null,
					    "\nEl precio de su compra es: "+total+"\nDesea realizar la compra",
					    "Desea realizar la compra",
					    JOptionPane.YES_NO_OPTION
					);
			}
			else {
				respuesta = JOptionPane.showConfirmDialog(
					    null,
					    "\nEl precio de su compra con descuento por ser trabajador es: "+total+"\nDesea realizar la compra",
					    "Desea realizar la compra",
					    JOptionPane.YES_NO_OPTION
					);
			}
			if (respuesta == JOptionPane.YES_OPTION) {
				parque.registrarCompraTiquetesOnline(usuario, tiquetes, fastPass);
				System.out.print("\nSu compra fue realizada exitosamente, disfrutela!\n");
				cerrarVentana();
			}
			else if (respuesta == JOptionPane.NO_OPTION) {
				System.out.print("\nEntendemos, su compra fue cancelada\n");
				cerrarVentana();
			}
		}
	}
}
