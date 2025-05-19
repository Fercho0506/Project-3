package InterfazPrincipal;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelAutenticarse extends JPanel{
	private JTextField usuario;
	private JTextField password;
	
	public PanelAutenticarse( )
    {
		setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));
    	JLabel ingresoUsuario = new JLabel("Ingrese su Usuario: (Máximo 20 caracteres)");
    	usuario = new JTextField(20);

        JLabel ingresoPassword = new JLabel("Ingrese su contraseña: (Máximo 20 caracteres)");
        password = new JTextField(20);
        
        add(ingresoUsuario); 
        add(usuario);
        add(ingresoPassword); 
        add(password); 
    }
	
	public String getUsuario() {
		return usuario.getText();
	}
	
	public String getPassword() {
		return password.getText();
	}
	
	
}
