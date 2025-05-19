package VentanasClientes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;

import Modelo.Parque;
import PanelesClientes.NotificationsListener;
import PanelesClientes.PanelDetalllesTiquetes;
import PanelesClientes.PanelListaTiquetes;
import PanelesClientes.PanelOpciones;
import Tiquetes.Tiquete;
import Usuarios.Usuario;

public class VentanaEntrarParque extends JFrame implements NotificationsListener{
	private VentanaClientesPrincipal ventanaPrincipal;
	private PanelListaTiquetes tiquetes;
	private PanelDetalllesTiquetes detalles;
	private PanelOpciones opciones;
	private Parque parque;
	private Usuario usuario;
	private VentanaParque ventanaParque;
	
	public VentanaEntrarParque(VentanaClientesPrincipal ventanaPrincipal, Parque parque, Usuario usuario) {
		this.ventanaPrincipal=ventanaPrincipal;
		this.parque=parque;
		this.usuario=usuario;
		setLayout( new BorderLayout( ) );
		
		detalles= new PanelDetalllesTiquetes();
		tiquetes= new PanelListaTiquetes(usuario, detalles);
		opciones= new PanelOpciones("Escoger tiquete", ventanaPrincipal, this);
		
		opciones.setAlignmentX(Component.CENTER_ALIGNMENT);
		tiquetes.setAlignmentX(Component.CENTER_ALIGNMENT);
		detalles.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(tiquetes, BorderLayout.NORTH);
		add(detalles, BorderLayout.CENTER);
		add(opciones, BorderLayout.SOUTH);
		
		actualizarTiquetes();
		
		setTitle("Escoja Tiquete Para Ingresar a Parque");
		
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
	
	private void actualizarTiquetes() {
		tiquetes.actualizarTiquetes();
        if (!usuario.getTiquetesPorUsar().isEmpty()) {
        	cambiarTiqueteSeleccionado(usuario.getTiquetesPorUsar().get(0));
        } else {
        	cambiarTiqueteSeleccionado(null);
        }
		
	}
	
	public void cambiarTiqueteSeleccionado(Tiquete tiquete) {
		tiquetes.seleccionarTiquete(tiquete);
	}

	@Override
	public void accionDesdePanel() {
		Tiquete tiquete= tiquetes.getTiquete();
		if (tiquete!=null) {
			Date fecha= new Date();
        	SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        	String texto = formato.format(fecha);
        	boolean valido= parque.registrarEntrada(tiquete, texto, usuario.getFastPassPorUsar());
        	if (valido) {
        		System.out.println("\nSu ingreso al parque fue exitoso! \n");
        		ventanaParque= new VentanaParque(ventanaPrincipal, parque, usuario, tiquete);
        		ventanaParque.setVisible(true);
        		dispose();
        	}
        	else {
        		System.out.println("\nLo sentimos pero su ingreso al parque no fue exitoso\n");
        		cerrarVentana();
        	}
		}
		else {
			System.out.println("\nPor favor escoja un tiquete\n");
		}
		
	}
	
	public void cerrarVentana() {
		ventanaPrincipal.setVisible(true);
		dispose();
	}
}
