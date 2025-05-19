package InterfazPrincipal;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelRegistrarse extends JPanel{
	private JTextField usuario;
	private JTextField password;
	private JTextField edad;
	private JTextField estatura;
	
	public PanelRegistrarse( )
    {
		setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));
    	JLabel ingresoUsuario = new JLabel("Escoja su Usuario: (Máximo 20 caracteres)");
    	usuario = new JTextField(20);

        JLabel ingresoPassword = new JLabel("Escoja su contraseña: (Máximo 20 caracteres)");
        password = new JTextField(20);
        
        JLabel ingresoEdad = new JLabel("Ingrese su edad: (ej. 20)");
        edad = new JTextField(2);
        
        JLabel ingresoEstatura = new JLabel("Ingrese su estatura: (ej: 1.75)");
        estatura = new JTextField(4);
        
        add(ingresoUsuario); 
        add(usuario);
        add(ingresoPassword); 
        add(password); 
        add(ingresoEdad); 
        add(edad);
        add(ingresoEstatura); 
        add(estatura);
    }
	
	public String getUsuario() {
		return usuario.getText();
	}
	
	public String getPassword() {
		return password.getText();
	}
	
	public String getEdad() {
		return edad.getText().strip();
	}
	
	public String getEstatura() {
		return estatura.getText().strip();
	}
}
