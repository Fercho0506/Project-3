package Usuarios;

public class Cliente extends Usuario{
	private static final long serialVersionUID = 1L;
	
	public Cliente(String login, String password, int edad, float altura) {
		super(login, password, "cliente", edad, altura);
	}
}
