package InterfazPrincipal;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import Modelo.Parque;
import Persistencia.Persistencia;

public class VentanaPrincipal extends JFrame{
	private static final String RUTA_ARCHIVO = "data/parque.bin";
	private Parque parque;
	private PanelOpciones panelOpciones;
	private VentanaAutenticarse autenticarse;
	private VentanaRegistrarse registrarse;
	
	public VentanaPrincipal(Parque parque) {
		this.parque=parque;
		setLayout( new BorderLayout( ) );
		
		panelOpciones= new PanelOpciones(this);
		panelOpciones.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(panelOpciones, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	Persistencia.guardarObjeto(parque, RUTA_ARCHIVO);
            }
        });
		
		setTitle( "BIENVENIDO A LA PLATAFORMA" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 300, 100 );
        setLocationRelativeTo( null );
        setVisible( true );
	}
	
	public void mostrarAutenticarse() {
		if (autenticarse == null || !autenticarse.isVisible( )) {
			autenticarse = new VentanaAutenticarse(this, parque);
			autenticarse.setVisible( true );
			this.setVisible(false);
		}
	}
	
	public void mostrarRegistrarse() {
		if (registrarse == null || !registrarse.isVisible( )) {
			registrarse = new VentanaRegistrarse(this, parque);
			registrarse.setVisible( true );
			this.setVisible(false);
		}
	}
	
	public static void main( String[] args ) {
		Parque park= null;
		try {
            park = (Parque) Persistencia.cargarObjeto(RUTA_ARCHIVO);
            // Validación extra por si cargarObjeto devuelve null
            if (park == null) {
                park = new Parque("Calle 123", "Diversiones S.A", 500);
            }  
        } catch (Exception e) {
            System.out.println("⚠️ Error al cargar el parque. Se creará uno nuevo.");
            e.printStackTrace();  // 🔍 Esto mostrará el error específico
            park = new Parque("Calle 123", "Diversiones S.A", 500);
        }
		new VentanaPrincipal (park);
	}
}
