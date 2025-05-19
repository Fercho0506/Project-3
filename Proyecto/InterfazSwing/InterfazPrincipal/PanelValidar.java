package InterfazPrincipal;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelValidar extends JPanel implements ActionListener{
	
	private static final String CONTINUAR = "continuar";
    private static final String CERRAR = "cerrar";
    private JButton butContinuar;
    private JButton butCerrar;
    private VentanaAutenticarse ventanaPrincipal;
    
    public PanelValidar( VentanaAutenticarse ventanaPrincipal )
    {
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout( new FlowLayout());

        butContinuar= new JButton("Ingresar");
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
            ventanaPrincipal.autenticarse( );
        }
        else if( comando.equals( CERRAR ) )
        {
            ventanaPrincipal.cerrarVentana( );
        }
    }
    
    
    
}
