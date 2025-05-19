package VentanasClientes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;

import LugarServicios.LugarServicio;
import LugarServicios.Taquilla;
import Modelo.Parque;
import PanelesClientes.PanelParque;
import Tiquetes.Tiquete;
import Usuarios.Usuario;

public class VentanaParque extends JFrame{
	private Parque parque;
	private Usuario usuario;
	private Tiquete tiquete;
	private VentanaClientesPrincipal ventanaPrincipal;
	private PanelParque panel;
	private VentanaIngresarAtraccion atraccion;
	private VentanaIngresarEspectaculo espectaculo;
	private VentanaEntradasTaquilla entradas;
	
	public VentanaParque(VentanaClientesPrincipal ventanaPrincipal, Parque parque, Usuario usuario, Tiquete tiquete) {
		this.ventanaPrincipal=ventanaPrincipal;
		this.parque=parque;
		this.usuario=usuario;
		this.tiquete=tiquete;
		
		panel= new PanelParque(this);
		
		setLayout( new BorderLayout( ) );
		
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(panel, BorderLayout.CENTER);
		
		setTitle("PARQUE");
		
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
	
	public void mostrarAtraccion() {
		if (atraccion==null || !atraccion.isVisible()) {
			if (parque.getAtracciones().size()>0) {
				atraccion= new VentanaIngresarAtraccion(this, parque, usuario);
				atraccion.setVisible(true);
				this.setVisible(false);
			}
			else {
				System.out.print("No hay atracciones para ingresar en el momento\n");
			}
		}
	}
	
	public void mostrarEspectaculo() {
		if (espectaculo==null || !espectaculo.isVisible()) {
			if (parque.getEspectaculos().size()>0) {
				espectaculo= new VentanaIngresarEspectaculo(this, parque, usuario);
				espectaculo.setVisible(true);
				this.setVisible(false);
			}
			else {
				System.out.print("No hay espectaculos para ingresar en el momento\n");
			}
		}
	}
	
	public void mostrarEntrada() {
		if (entradas==null || !entradas.isVisible()) {
			Taquilla taquilla=null;
			for (LugarServicio i: parque.getLugares()) {
				if (i.getTipo().compareTo("taquilla")==0) {
					taquilla= (Taquilla)i;
				}
			}
			if (parque.getAtracciones().size()>0 && taquilla!=null) {
				entradas= new VentanaEntradasTaquilla(this, parque, usuario, taquilla);
				entradas.setVisible(true);
				this.setVisible(false);
			}
			else {
				System.out.print("No hay atracciones para ingresar en el momento o no hay taquillas\n");
			}
		}
	}
	
	public void salirParque() {
		Date fecha= new Date();
    	SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    	String texto = formato.format(fecha);
		System.out.println("\nLo esperamos pronto! \n");
    	parque.registrarSalida(tiquete, texto, usuario.getFastPassPorUsar());
    	cerrarVentana();
	}
	
	public void cerrarVentana() {
		ventanaPrincipal.setVisible(true);
		dispose();
	}
}
