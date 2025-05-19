package VentanasClientes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import Modelo.Parque;
import PanelesClientes.PanelClientesPrincipal;
import Usuarios.Usuario;

public class VentanaClientesPrincipal extends JFrame{
	private Parque parque;
	private Usuario usuario;
	private JFrame ventanaAnterior;
	private PanelClientesPrincipal panel;
	private VentanaComprarTiquetes comprarTiquetes;
	private VentanaCompras compras;
	private VentanaVerTiquetes verTiquetes;
	private VentanaEntrarParque entrarParque;
	// private VentanaPassword password;
	
	public VentanaClientesPrincipal(Parque parque, Usuario usuario, JFrame ventana) {
		this.parque=parque;
		this.usuario=usuario;
		this.ventanaAnterior=ventana;
		
		panel= new PanelClientesPrincipal(this);
		
		setLayout( new BorderLayout( ) );
		
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(panel, BorderLayout.CENTER);
		
		setTitle("PANTALLA CLIENTES");
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	cerrarVentana();
            }
        });
		
		setSize(300,200);
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
	}
	
	public void mostrarComprarTiquetes() {
		if (comprarTiquetes==null || !comprarTiquetes.isVisible()) {
			comprarTiquetes= new VentanaComprarTiquetes(parque, usuario, this);
			comprarTiquetes.setVisible(true);
			this.setVisible(false);
		}
	}
	
	public void mostrarTiquetes() {
		if (verTiquetes==null || !verTiquetes.isVisible()) {
			if (usuario.getTiquetesPorUsar().size()>0) {
				verTiquetes= new VentanaVerTiquetes(this, usuario);
				verTiquetes.setVisible(true);
				this.setVisible(false);
			}
			else {
				System.out.println("\nNo tiene tiquetes disponibles en este momento\n");
			}
		}
			
	}
	
	public void mostrarCompras() {
		if (compras==null || !compras.isVisible()) {
			if (usuario.getHistorialCompras().size()>0) {
				compras= new VentanaCompras(usuario, this);
				compras.setVisible(true);
				this.setVisible(false);
			}
			else {
				System.out.println("\nNo ha realizado compras hasta el momento\n");
			}
		}
	}
	
	public void mostrarEntrarParque() {
		if (entrarParque==null || !entrarParque.isVisible()) {
			if (usuario.getTiquetesPorUsar().size()>0) {
				entrarParque= new VentanaEntrarParque(this, parque, usuario);
				entrarParque.setVisible(true);
				this.setVisible(false);
			}
			else {
				System.out.println("\nNo tiene tiquetes disponibles en este momento\n");
			}
		}
	}
	
	public void cerrarVentana() {
		ventanaAnterior.setVisible(true);
		dispose();
	}
}
