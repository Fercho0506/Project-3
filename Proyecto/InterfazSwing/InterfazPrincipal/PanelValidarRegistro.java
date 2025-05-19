package InterfazPrincipal;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelValidarRegistro extends JPanel implements ActionListener{
	private static final String CONTINUAR = "registrarse";
    private static final String CERRAR = "cerrar";
    private JButton butContinuar;
    private JButton butCerrar;
    private VentanaRegistrarse ventanaPrincipal;
    
    public PanelValidarRegistro( VentanaRegistrarse ventanaPrincipal )
    {
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout( new FlowLayout());

        butContinuar= new JButton("Registrarse");
        butContinuar.setActionCommand(CONTINUAR); 
        butContinuar.addActionListener(this); 
        add(butContinuar); 

        butCerrar= new JButton("Volver a principal");
        butCerrar.setActionCommand(CERRAR); 
        butCerrar.addActionListener(this); 
        add(butCerrar); 
    }
    
    @Override
    public void actionPerformed( ActionEvent e ) {
    	String comando = e.getActionCommand( );
        if( comando.equals( CONTINUAR ) )
        {
            ventanaPrincipal.registrarse( );
        }
        else if( comando.equals( CERRAR ) )
        {
            ventanaPrincipal.cerrarVentana( );
        }
    }
}
