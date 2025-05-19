package VentanasClientes;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import Atracciones.Atraccion;
import Atracciones.Espectaculo;
import Modelo.Parque;
import PanelesClientes.NotificationsListener;
import PanelesClientes.PanelDetalllesTiquetes;
import PanelesClientes.PanelListaAtraccion;
import PanelesClientes.PanelListaEspectaculos;
import PanelesClientes.PanelListaTiquetes;
import PanelesClientes.PanelOpciones;
import Tiquetes.Tiquete;
import Usuarios.Usuario;

public class VentanaIngresarEspectaculo extends JFrame implements NotificationsListener{
	private VentanaParque ventanaPrincipal;
	private PanelOpciones opciones;
	private PanelListaEspectaculos espectaculos;
	private Parque parque;
	private Usuario usuario;
	
	public VentanaIngresarEspectaculo(VentanaParque ventanaPrincipal, Parque parque, Usuario usuario) {
		this.parque=parque;
		this.ventanaPrincipal=ventanaPrincipal;
		this.usuario=usuario;
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		opciones= new PanelOpciones("Ingresar", ventanaPrincipal, this);
		espectaculos= new PanelListaEspectaculos(parque);
		
		add(espectaculos);
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
		Espectaculo espectaculo= espectaculos.getEspectaculo();
		if (espectaculo!= null ) {
			if (espectaculo.getAbierto() && espectaculo.verificarFecha()) {
				System.out.println("\nIngresó correctamente al espectáculo\n");
			}
			else {
				System.out.println("\nLo sentimos, el espectáculo esta cerrado\n");
			}
			cerrarVentana();
		}
		else {
			System.out.print("\nPor favor escoger el espectaculo que desee\n");
		}
		
	}
	
	private void actualizarDatos() {
		espectaculos.actualizarEspectaculos();
		cambiarEspectaculoSeleccionado(parque.getEspectaculos().get(0));
	}
	
	public void cambiarEspectaculoSeleccionado(Espectaculo espectaculo) {
		espectaculos.seleccionarEspectaculo(espectaculo);
	}
	
	private void cerrarVentana() {
		ventanaPrincipal.setVisible(true);
		dispose();
	}

}
