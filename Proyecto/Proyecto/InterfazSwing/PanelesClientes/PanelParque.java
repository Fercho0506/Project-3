package PanelesClientes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Tiquetes.FastPass;
import VentanasClientes.VentanaParque;

public class PanelParque extends JPanel implements ActionListener{
	private static final String ATRACCION = "atraccion";
	private static final String ESPECTACULO = "espectaculo";
	private static final String CERRAR = "salir";
	private static final String ENTRADA = "entrada";
	private JButton atraccion;
	private JButton espectaculo;
	private JButton salir;
	private JButton entrada;
	private VentanaParque ventanaPrincipal;
	
	public PanelParque(VentanaParque ventanaPrincipal) {
		this.ventanaPrincipal=ventanaPrincipal;
		setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel titulo= new JLabel("ACTIVIDADES QUE HACER");
		
		atraccion= new JButton("Ingresar a una Atracción");
		espectaculo= new JButton("Ingresar a un espectaculo");
		entrada= new JButton("Comprar Entrada en Taquilla");
		salir= new JButton("Salir del parque");

		atraccion.setActionCommand(ATRACCION);
		espectaculo.setActionCommand(ESPECTACULO);
		entrada.setActionCommand(ENTRADA);
		salir.setActionCommand(CERRAR);
		
		atraccion.addActionListener(this);
		espectaculo.addActionListener(this);
		entrada.addActionListener(this);
		salir.addActionListener(this);
        
		atraccion.setAlignmentX(Component.CENTER_ALIGNMENT);
		espectaculo.setAlignmentX(Component.CENTER_ALIGNMENT); 
        entrada.setAlignmentX(Component.CENTER_ALIGNMENT);
        salir.setAlignmentX(Component.CENTER_ALIGNMENT); 
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT); 
        
        add(titulo);
        add(atraccion);
        add(espectaculo);
        add(entrada);
        add(salir);
	}
	
	@Override
    public void actionPerformed( ActionEvent e ) {
		String comando = e.getActionCommand( );
		if (ATRACCION.equals(comando)) {
			ventanaPrincipal.mostrarAtraccion();
		}
		else if (ESPECTACULO.equals(comando)) {
			ventanaPrincipal.mostrarEspectaculo();
		}
		else if (ENTRADA.equals(comando)) {
			ventanaPrincipal.mostrarEntrada();
		}
		else if (CERRAR.equals(comando)) {
			ventanaPrincipal.salirParque();
		}
	}
}
