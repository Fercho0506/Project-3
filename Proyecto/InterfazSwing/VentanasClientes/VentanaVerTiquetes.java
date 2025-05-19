package VentanasClientes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import PanelesClientes.NotificationsListener;
import PanelesClientes.PanelDetalllesTiquetes;
import PanelesClientes.PanelListaTiquetes;
import PanelesClientes.PanelOpciones;
import Tiquetes.Tiquete;
import Usuarios.Usuario;

public class VentanaVerTiquetes extends JFrame implements NotificationsListener{
	private VentanaClientesPrincipal ventanaPrincipal;
	private PanelListaTiquetes tiquetes;
	private PanelDetalllesTiquetes detalles;
	private PanelOpciones opciones;
	private VentanaImprimirTiquete ventanaImprimir;
	private Usuario usuario;
	
	public VentanaVerTiquetes(VentanaClientesPrincipal ventanaPrincipal, Usuario usuario) {
		this.ventanaPrincipal=ventanaPrincipal;
		this.usuario=usuario;
		setLayout( new BorderLayout( ) );
		
		detalles= new PanelDetalllesTiquetes();
		tiquetes= new PanelListaTiquetes(usuario, detalles);
		opciones= new PanelOpciones("Imprimir Tiquete", ventanaPrincipal, this);
		
		opciones.setAlignmentX(Component.CENTER_ALIGNMENT);
		tiquetes.setAlignmentX(Component.CENTER_ALIGNMENT);
		detalles.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(tiquetes, BorderLayout.NORTH);
		add(detalles, BorderLayout.CENTER);
		add(opciones, BorderLayout.SOUTH);
		
		actualizarTiquetes();
		
		setTitle("Tiquetes disponibles");
		
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
	
	private void cerrarVentana() {
		ventanaPrincipal.setVisible(true);
		dispose();
	}

	@Override
	public void accionDesdePanel() {
		Tiquete tiquete= tiquetes.getTiquete();
		int respuesta=0;
		if (ventanaImprimir==null || !ventanaImprimir.isVisible()) {
			if (tiquete.getImpreso()) {
				respuesta = JOptionPane.showConfirmDialog(
					    null,
					    "\nDesea imprimir el tiquete nuevamente",
					    "",
					    JOptionPane.YES_NO_OPTION
					);
				if (respuesta == JOptionPane.YES_OPTION) {
					tiquete.imprimir();
					ventanaImprimir= new VentanaImprimirTiquete(tiquete, usuario);
					ventanaImprimir.setVisible(true);
				}
			}
			else {
				tiquete.imprimir();
				ventanaImprimir= new VentanaImprimirTiquete(tiquete, usuario);
				ventanaImprimir.setVisible(true);
			}
		}
	}
}
