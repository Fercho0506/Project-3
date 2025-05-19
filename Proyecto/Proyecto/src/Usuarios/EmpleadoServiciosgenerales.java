package Usuarios;

import LugarServicios.Cafeteria;
import LugarServicios.LugarServicio;

public class EmpleadoServiciosgenerales extends Empleado {
	
	private static final long serialVersionUID = 1L;
	
	private LugarServicio lugar;
	public EmpleadoServiciosgenerales (String login, String password, int edad, float altura, Cafeteria cafeteria, String labor) {
		super(login, password, edad, altura, "serviciosGenerales", labor);
		this.AsignarTurno("N/A");
		this.lugar=null;
	}
	
	public LugarServicio getLugar() {
		return lugar;
	}
	
	public void setLugar(LugarServicio lugare) {
		lugar=lugare;
	}
}
