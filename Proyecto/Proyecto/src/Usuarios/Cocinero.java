package Usuarios;

import LugarServicios.Cafeteria;

public class Cocinero extends Empleado{
	private static final long serialVersionUID = 1L;
	private Cafeteria cafeteria;
	
	public Cocinero (String login, String password, int edad, float altura, String labor) {
		super(login, password, edad, altura, "cocinero", labor);
		this.cafeteria=null;
	}
	
	public Cafeteria getCafeteria() {
		return cafeteria;
	}
	
	public void setCafeteria(Cafeteria cafeteria) {
		this.cafeteria=cafeteria;
	}
	
	public void prepararPlato(String plato) {
		System.out.print("Se preparó "+plato+"plato");
	}
	
}
