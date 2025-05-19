package VentanasClientes;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import PanelesClientes.NotificationsListener;
import PanelesClientes.PanelEscogerExclusividad;
import PanelesClientes.PanelEscogerTemporada;
import PanelesClientes.PanelOpciones;
import Tiquetes.Tiquete;
import Tiquetes.TiqueteTemporada;
import Usuarios.Usuario;

public class VentanaTiquetesTemporada extends JFrame implements NotificationsListener{
	private static final int basico= 20;
	private static final int familiar= 30;
	private static final int oro= 40;
	private static final int diamante= 50;
	private ArrayList<Tiquete> tiquetes;
	private PanelOpciones opciones;
	private PanelEscogerExclusividad exclusividad;
	private PanelEscogerTemporada temporada;
	private VentanaComprarTiquetes ventanaPrincipal;
	private Usuario usuario;
	
	public VentanaTiquetesTemporada(ArrayList<Tiquete> tiquetes, VentanaComprarTiquetes ventanaPrincipal, Usuario usuario) {
		this.usuario=usuario;
		this.tiquetes=tiquetes;
		this.ventanaPrincipal=ventanaPrincipal;
		setLayout( new BorderLayout( ) );
		
		opciones= new PanelOpciones("Agregar Tiquete", ventanaPrincipal, this);
		exclusividad = new PanelEscogerExclusividad();
		temporada = new PanelEscogerTemporada();
		
		add(exclusividad, BorderLayout.NORTH);
		add(temporada, BorderLayout.CENTER);
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
		if (!exclusividad.getOpcion().equals("") && !temporada.getOpcion().equals("")) {
			int precio= getExclusividad();
			String [] fechas= getTemporada();
			TiqueteTemporada tiquete= new TiqueteTemporada(exclusividad.getOpcion(), usuario, precio, fechas[0], fechas[1]);
			tiquetes.addLast(tiquete);
			ventanaPrincipal.actualizarDetalles();
			cerrarVentana();
		}
		else {
			System.out.println("\nPor favor rellene todos los campos\n");
		}
	}
		
	private int getExclusividad() {
		int precio=0;
		if (exclusividad.getOpcion().equals("basico")) {
			precio=200+basico;
		}
		else if (exclusividad.getOpcion().equals("familiar")) {
			precio=200+familiar;
		} 
		else if (exclusividad.getOpcion().equals("oro")) {
			precio=200+oro;
		}
		else {
			precio=200+diamante;
		}
		return precio;
	}
	
	private String[] getTemporada() {
		String[] fechas=  new String[2];
		if (temporada.getOpcion().equals("primavera")) {
			fechas[0]="01-03";
			fechas[1]= "31-05";
		}
		else if (temporada.getOpcion().equals("verano")) {
			fechas[0]="01-06";
			fechas[1]= "30-08";
		} 
		else if (temporada.getOpcion().equals("otoño")) {
			fechas[0]="01-09";
			fechas[1]= "30-11";
		}
		else{
			fechas[0]="01-12";
			fechas[1]= "28-02";
		}
		return fechas;
	}
		
	
	
	public void cerrarVentana() {
		ventanaPrincipal.setVisible(true);
		dispose();
	}
}
