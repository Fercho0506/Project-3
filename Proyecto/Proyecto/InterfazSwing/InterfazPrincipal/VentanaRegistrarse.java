package InterfazPrincipal;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import Modelo.Parque;
import Usuarios.Cliente;

public class VentanaRegistrarse extends JFrame{
	private PanelRegistrarse opciones;
	private VentanaPrincipal ventanaPrincipal;
	private Parque parque;
	private PanelValidarRegistro registro;
	
	public VentanaRegistrarse( VentanaPrincipal principal , Parque parque)
    {
        this.ventanaPrincipal = principal;
        this.parque=parque;
        setLayout( new BorderLayout( ) );
        
        opciones= new PanelRegistrarse();
        add(opciones, BorderLayout.CENTER);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	cerrarVentana();
            }
        });
        
        registro= new PanelValidarRegistro(this);
        add(registro, BorderLayout.SOUTH);
        setTitle("Ingrese su usuario y contraseña por favor");
      
        pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
    }
	
	public void registrarse() {
		int edad=0;
    	float altura=0;
		
		String login= opciones.getUsuario();
		String password = opciones.getPassword();
		try {
    		edad = Integer.parseInt(opciones.getEdad());
    		try {
        		altura=Float.parseFloat(opciones.getEstatura());
            	Cliente c1= new Cliente(login, password, edad, altura);
            	try {
            		parque.agregarUsuario(c1);
                	System.out.println("\nSu registro a la plataforma como cliente fue exitoso!\n");
                	ventanaPrincipal.setVisible(true);
                	dispose();
            	}catch (Exception o) {
            		System.out.println(o.getMessage());
            	}
            	
        	} catch(Exception a) {
        		System.out.println("\nPor favor ingrese su altura correctamente\n");
        	}
    	}catch(Exception e) {
        	System.out.println("\nPor favor ingrese el número de su edad correctamente\n");
    	}
	}
	
	public void cerrarVentana() {
		ventanaPrincipal.setVisible(true);
		dispose();
	}
}
