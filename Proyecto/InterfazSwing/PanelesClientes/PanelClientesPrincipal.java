package PanelesClientes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import VentanasClientes.VentanaClientesPrincipal;
import VentanasClientes.VentanaParque;

public class PanelClientesPrincipal extends JPanel implements ActionListener{
	private static final String COMPRAR = "comprar";
	private static final String TIQUETES = "tiquetes";
	private static final String COMPRAS = "compras";
	private static final String ENTRAR = "entrar";
	private static final String PASSWORD = "password";
	private static final String SALIR = "salir";
	private JButton comprar;
	private JButton tiquetes;
	private JButton salir;
	private JButton entrar;
	//private JButton password;
	private JButton compras;
	private VentanaClientesPrincipal ventanaPrincipal;
	
	public PanelClientesPrincipal(VentanaClientesPrincipal ventanaPrincipal) {
		this.ventanaPrincipal=ventanaPrincipal;
		setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel titulo= new JLabel("MENU CLIENTE");
		
		comprar= new JButton("Comprar tiquetes");
		tiquetes= new JButton("Ver tiquetes disponibles");
		compras= new JButton("Ver historial de compras");
		entrar = new JButton("Entrar a parque");
		//password = new JButton("Cambiar contraseña");
		salir= new JButton("Salir a ventana anterior");

		comprar.setActionCommand(COMPRAR);
		tiquetes.setActionCommand(TIQUETES);
		compras.setActionCommand(COMPRAS);
		entrar.setActionCommand(ENTRAR);
		//password.setActionCommand(CERRAR)
		salir.setActionCommand(SALIR);
		
		comprar.addActionListener(this);
		tiquetes.addActionListener(this);
		compras.addActionListener(this);
		entrar.addActionListener(this);
		//password.addActionListener(this);
		salir.addActionListener(this);
        
		comprar.setAlignmentX(Component.CENTER_ALIGNMENT);
		tiquetes.setAlignmentX(Component.CENTER_ALIGNMENT); 
		compras.setAlignmentX(Component.CENTER_ALIGNMENT);
		entrar.setAlignmentX(Component.CENTER_ALIGNMENT); 
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT); 
        salir.setAlignmentX(Component.CENTER_ALIGNMENT); 
        //password.setAlignmentX(Component.CENTER_ALIGNMENT); 
        
        add(titulo);
        add(comprar);
        add(tiquetes);
        add(compras);
        add(entrar);
        //add(password);
        add(salir);
	}
	
	@Override
    public void actionPerformed( ActionEvent e ) {
		String comando = e.getActionCommand( );
		if (COMPRAR.equals(comando)) {
			ventanaPrincipal.mostrarComprarTiquetes();
		}
		else if (TIQUETES.equals(comando)) {
			ventanaPrincipal.mostrarTiquetes();
		}
		else if (COMPRAS.equals(comando)) {
			ventanaPrincipal.mostrarCompras();
		}
		else if (ENTRAR.equals(comando)) {
			ventanaPrincipal.mostrarEntrarParque();
		}
		//else if (PASSWORD.equals(comando)) {
		//	ventanaPrincipal.mostrarPassword();
		//}
		else if (SALIR.equals(comando)) {
			System.out.print("\nLo esperamos pronto\n");
			ventanaPrincipal.cerrarVentana();
		}
	}
}
