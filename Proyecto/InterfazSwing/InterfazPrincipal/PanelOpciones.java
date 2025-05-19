package InterfazPrincipal;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelOpciones extends JPanel implements ActionListener{
	private static final String ENTRAR = "Autenticarse";
    private static final String REGISTRAR = "Registrarse";
    private JButton butAutenticar;
    private JButton butRegistrar;
    private VentanaPrincipal ventanaPrincipal;
    
    public PanelOpciones( VentanaPrincipal ventanaPrincipal )
    {
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));

        butAutenticar= new JButton("Ingresar");
        butAutenticar.setActionCommand(ENTRAR); 
        butAutenticar.addActionListener(this); 

        butRegistrar= new JButton("Registrarse");
        butRegistrar.setActionCommand(REGISTRAR); 
        butRegistrar.addActionListener(this); 
        
        butRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        butAutenticar.setAlignmentX(Component.CENTER_ALIGNMENT); 
        
        add(butAutenticar); 
        add(Box.createVerticalStrut(10));
        add(butRegistrar); 
        
    }
    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( ENTRAR ) )
        {
            ventanaPrincipal.mostrarAutenticarse( );
        }
        else if( comando.equals( REGISTRAR ) )
        {
            ventanaPrincipal.mostrarRegistrarse( );
        }
    }
}
