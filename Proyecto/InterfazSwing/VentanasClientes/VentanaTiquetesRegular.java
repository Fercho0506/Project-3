package VentanasClientes;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import PanelesClientes.NotificationsListener;
import PanelesClientes.PanelEscogerExclusividad;
import PanelesClientes.PanelOpciones;
import Tiquetes.Tiquete;
import Tiquetes.TiqueteRegular;
import Usuarios.Usuario;

public class VentanaTiquetesRegular extends JFrame implements NotificationsListener{
	private static final int basico= 20;
	private static final int familiar= 30;
	private static final int oro= 40;
	private static final int diamante= 50;
	private ArrayList<Tiquete> tiquetes;
	private PanelOpciones opciones;
	private PanelEscogerExclusividad exclusividad;
	private VentanaComprarTiquetes ventanaPrincipal;
	private Usuario usuario;
	
	public VentanaTiquetesRegular(ArrayList<Tiquete> tiquetes, VentanaComprarTiquetes ventanaPrincipal, Usuario usuario) {
		this.usuario=usuario;
		this.tiquetes=tiquetes;
		this.ventanaPrincipal=ventanaPrincipal;
		setLayout( new BorderLayout( ) );
		
		opciones= new PanelOpciones("Agregar Tiquete", ventanaPrincipal, this);
		exclusividad = new PanelEscogerExclusividad();
		
		add(exclusividad, BorderLayout.CENTER);
		add(opciones, BorderLayout.SOUTH);
		setTitle("Escoja la exclusividad de su Tiquete regular");
		
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
	
	@Override
	public void accionDesdePanel() {
		if (!exclusividad.getOpcion().equals("")) {
			TiqueteRegular tiquete= null;
			if (exclusividad.getOpcion().equals("basico")) {
				tiquete= new TiqueteRegular(exclusividad.getOpcion(), usuario, basico);
			}
			else if (exclusividad.getOpcion().equals("familiar")) {
				tiquete= new TiqueteRegular(exclusividad.getOpcion(), usuario, familiar);
			} 
			else if (exclusividad.getOpcion().equals("oro")) {
				tiquete= new TiqueteRegular(exclusividad.getOpcion(), usuario, oro);
			}
			else {
				tiquete= new TiqueteRegular(exclusividad.getOpcion(), usuario, diamante);
			}
			tiquetes.addLast(tiquete);
			ventanaPrincipal.actualizarDetalles();
			cerrarVentana();
		}
		else {
			System.out.println("\nPor favor rellene todos los campos\n");
		}
		
	}
	
	public void cerrarVentana() {
		ventanaPrincipal.setVisible(true);
		dispose();
	}
	
}
