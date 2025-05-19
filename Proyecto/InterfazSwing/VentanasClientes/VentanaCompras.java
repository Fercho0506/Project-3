package VentanasClientes;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Modelo.Compra;
import PanelesClientes.PanelDetallesCompras;
import PanelesClientes.PanelListaCompras;
import Usuarios.Usuario;

public class VentanaCompras extends JFrame implements ActionListener{
	private static final String VOLVER = "volver";
	private Usuario usuario;
	private VentanaClientesPrincipal ventanaPrincipal;
	private PanelDetallesCompras detalles;
	private PanelListaCompras compras;
	private JButton volver;
	
	public VentanaCompras(Usuario usuario, VentanaClientesPrincipal ventanaPrincipal) {
		this.usuario=usuario;
		this.ventanaPrincipal=ventanaPrincipal;
		setLayout( new BorderLayout( ) );
		detalles= new PanelDetallesCompras();
		compras= new PanelListaCompras(usuario, this);
	
		volver= new JButton("Volver");	
		volver.setActionCommand(VOLVER); 
        volver.addActionListener(this); 
        
		add(compras,  BorderLayout.NORTH);
		
		JPanel panel= new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(detalles);
		panel.add(volver);
		
		add(panel,  BorderLayout.SOUTH);
		setTitle("Su historial de compras");
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	cerrarVentana();
            }
        });
		
		actualizarCompras();
		
		pack( );
        setResizable( false );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setLocationRelativeTo( null );
	}
	
	
	private void actualizarCompras() {
		compras.actualizarCompras();
        if (!usuario.getHistorialCompras().isEmpty()) {
            cambiarCompraSeleccionada(usuario.getHistorialCompras().get(0));
        } else {
            detalles.actualizarCompra(null);
        }
		
	}


	public void cerrarVentana() {
		ventanaPrincipal.setVisible(true);
		 dispose();
	}
	
	public void cambiarCompraSeleccionada(Compra compra) {
		detalles.actualizarCompra( compra );
	}
	
	@Override
    public void actionPerformed( ActionEvent e ) {
		String comando = e.getActionCommand( );
        if( comando.equals( VOLVER )) {
        	cerrarVentana();
        }
	}
}
