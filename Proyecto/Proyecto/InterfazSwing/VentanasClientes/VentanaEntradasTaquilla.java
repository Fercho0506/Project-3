package VentanasClientes;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import Atracciones.Atraccion;
import Atracciones.AtraccionMecanica;
import LugarServicios.Taquilla;
import Modelo.Parque;
import PanelesClientes.NotificationsListener;
import PanelesClientes.PanelListaAtraccion;
import PanelesClientes.PanelOpciones;
import Tiquetes.Entrada;
import Tiquetes.FastPass;
import Tiquetes.Tiquete;
import Usuarios.Usuario;

public class VentanaEntradasTaquilla extends JFrame implements NotificationsListener{
	private static final int PRECIO_MEDIO= 10;
	private static final int PRECIO_ALTO= 20;
	private ArrayList<Tiquete> tiquetes;
	private PanelOpciones opciones;
	private PanelListaAtraccion atracciones;
	private VentanaParque ventanaPrincipal;
	private Usuario usuario;
	private Taquilla taquilla;
	private Parque parque;
	
	public VentanaEntradasTaquilla(VentanaParque ventanaPrincipal, Parque parque, Usuario usuario,
			Taquilla taquilla) {
		this.tiquetes= new ArrayList<Tiquete>();
		this.taquilla=taquilla;
		this.ventanaPrincipal=ventanaPrincipal;
		this.usuario=usuario;
		this.parque=parque;
		setLayout( new BorderLayout( ) );
		
		opciones= new PanelOpciones("Comprar Entrada", ventanaPrincipal, this);
		atracciones= new PanelListaAtraccion(parque);
		
		add(atracciones, BorderLayout.CENTER);
		add(opciones, BorderLayout.SOUTH);
		setTitle("Escoja la atracción para su entrada");
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	cerrarVentana();
            }
        });
		
		actualizarAtracciones(parque);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
	}
	
	private void actualizarAtracciones(Parque parque) {
		atracciones.actualizarAtracciones();
		cambiarAtraccionSeleccionada(parque.getAtracciones().get(0));
	}
	
	public void cambiarAtraccionSeleccionada( Atraccion seleccionado )
    {
		atracciones.seleccionarAtraccion(seleccionado);
    }
	
	public void cerrarVentana() {
		ventanaPrincipal.setVisible(true);
		dispose();
	}

	@Override
	public void accionDesdePanel() {
		Atraccion atraccion= atracciones.getAtraccion();
		int precio=0;
		List<FastPass> fast= new ArrayList<FastPass>();
		if (atraccion != null) {
			if (atraccion.getTipo().compareTo("mecanica")==0) {
				AtraccionMecanica atract= (AtraccionMecanica) atraccion;
				if (atract.getNivel().compareTo("alto")==0) {
					precio= PRECIO_ALTO;
					Entrada entrada= new Entrada(usuario, precio, atract);
					tiquetes.addLast(entrada);
				}
				else {
					precio= PRECIO_MEDIO;
					Entrada entrada= new Entrada(usuario, precio, atract);
					tiquetes.addLast(entrada);
				}
			}
			else {
				precio= PRECIO_MEDIO;
				Entrada entrada= new Entrada(usuario, precio, atraccion);
				tiquetes.addLast(entrada);
			}
			try {
				parque.registrarCompraTiquetesTaquilla(usuario, tiquetes, fast, taquilla);
				System.out.print("\nSu entrada fue adquirida exitosamente\n");
				
			}
			catch(Exception e) {
				System.out.print("\nLa taquilla no cuenta con un cajero para atender la compra, lo sentimos\n");
			}
			cerrarVentana();
		}
		else {
			System.out.println("\nPor favor escoja una atracción\n");
		}
	}

}
