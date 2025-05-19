package PanelesClientes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Tiquetes.FastPass;
import Tiquetes.Tiquete;
import Usuarios.Usuario;
import VentanasClientes.VentanaComprarTiquetes;

public class PanelComprarTiquetes extends JPanel implements ActionListener{
	private static final String REGULAR = "regular";
    private static final String TEMPORADA = "temporada";
    private static final String ENTRADA = "entrada";
    private static final String FAST = "fast";
	private JButton regular;
	private JButton temporada;
	private JButton entrada;
	private JButton fast;
	private JLabel detalles;
	private VentanaComprarTiquetes ventanaPrincipal;
	private ArrayList<Tiquete> tiquetes;
	private  ArrayList<FastPass> fastPass;
	private Usuario usuario;
	
	public PanelComprarTiquetes(VentanaComprarTiquetes ventanaPrincipal, ArrayList<Tiquete> tiquetes, ArrayList<FastPass> fastPass,
			Usuario usuario) {
		this.ventanaPrincipal=ventanaPrincipal;
		this.tiquetes=tiquetes;
		this.fastPass=fastPass;
		this.usuario=usuario;
		setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel titulo= new JLabel("Escoger tipo de tiquete");
		detalles = new JLabel("Por el momento tiene "+tiquetes.size()+" tiquetes y "+fastPass.size()+ " FastPass en esta compra");
		
		regular= new JButton("Regular");
		temporada= new JButton("Temporada");
		entrada= new JButton("Entrada");
		fast= new JButton("FastPass");

		regular.setActionCommand(REGULAR);
		temporada.setActionCommand(TEMPORADA);
		entrada.setActionCommand(ENTRADA);
		fast.setActionCommand(FAST);
		
		regular.addActionListener(this);
		temporada.addActionListener(this);
		entrada.addActionListener(this);
		fast.addActionListener(this);
        
        regular.setAlignmentX(Component.CENTER_ALIGNMENT);
        temporada.setAlignmentX(Component.CENTER_ALIGNMENT); 
        entrada.setAlignmentX(Component.CENTER_ALIGNMENT);
        fast.setAlignmentX(Component.CENTER_ALIGNMENT); 
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT); 
        detalles.setAlignmentX(Component.CENTER_ALIGNMENT); 
        
        add(titulo);
        add(regular);
        add(temporada);
        add(entrada);
        add(fast);
        add(detalles);
	}
	
	@Override
    public void actionPerformed( ActionEvent e ) {
		String comando = e.getActionCommand( );
		if (REGULAR.equals(comando)) {
			ventanaPrincipal.mostrarRegular();
		}
		else if (TEMPORADA.equals(comando)) {
			ventanaPrincipal.mostrarTemporada();
		}
		else if (ENTRADA.equals(comando)) {
			ventanaPrincipal.mostrarEntrada();
		}
		else if (FAST.equals(comando)) {
			Date fecha= new Date();
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			String dia = formato.format(fecha);
			FastPass fast= new FastPass(dia, usuario, 15);
			fastPass.addLast(fast);
			setDetalles();
		}
	}
	
	public void setDetalles() {
		detalles.setText("Por el momento tiene "+tiquetes.size()+" tiquetes y "+fastPass.size()+ " FastPass en esta compra"); 
	}
	
}
