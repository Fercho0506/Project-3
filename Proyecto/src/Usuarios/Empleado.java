package Usuarios;

import java.util.ArrayList;
import java.util.List;

public abstract class Empleado extends Usuario{
	private static final long serialVersionUID = 1L;
	private String tipoEmpleado;
	private List<String> turnos;
	private String labor;
	
	public Empleado(String login, String password, int edad, float altura, String tipoem,
			String labor) {
		super(login, password, "empleado", edad, altura);
		this.tipoEmpleado=tipoem;
		this.turnos= new ArrayList<String>();
		this.labor=labor;
	}
	
	public String getLabor() {
		return labor;
	}
	
	public void setLabor(String labor) {
		this.labor=labor;
	}
	
	public void AsignarTurno(String turno) {
		turnos.addLast(turno);
	}
	
	public String gettipo() {
		return tipoEmpleado;
	}
	
	public List<String> getTurnos(){
		return turnos;
	}
	
	public void RetirarTurno(String turno) {
		int i=0;
		boolean noEncontrado= true;
		while (i< turnos.size() && noEncontrado) {
			String turn= turnos.get(i);
			if (turn.equals(turno)) {
				turnos.remove(i);
				noEncontrado=false;
			}
			i++;
		}
	}

	public Object getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getRol() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setNombre(String nuevoNombre) {
		// TODO Auto-generated method stub
		
	}

	public void setRol(String nuevoRol) {
		// TODO Auto-generated method stub
		
	}

}
