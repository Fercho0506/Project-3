package Usuarios;

import java.util.ArrayList;
import java.util.List;

import Atracciones.Atraccion;
import Atracciones.AtraccionMecanica;

public class EmpleadoAtracciones extends Empleado{
	private static final long serialVersionUID = 1L;
	private List<Atraccion> atraccionesCapacitadas;
	private String nivelRiesgo;
	private Atraccion atraccion;
	
	public EmpleadoAtracciones(String login, String password, int edad, float altura,
			String labor, String nivel) {
		super(login, password, edad, altura, "empleadoAtracciones", labor);
		this.atraccion=null;
		this.nivelRiesgo=nivel;
		this.atraccionesCapacitadas= new ArrayList<Atraccion>();
	}
	
	public String getnivel() {
		return nivelRiesgo;
	}
	
	public Atraccion getAtraccion() {
		return atraccion;
	}
	
	public void setNivel(String nivel) {
		nivelRiesgo=nivel;
	}
	
	public void setAtraccion(Atraccion atract) throws Exception {
		if (atract.getTipo().equals("mecanica")) {
			AtraccionMecanica Atraccion= (AtraccionMecanica) atract;
			if (Atraccion.getNivel().equals("medio")) {
				atraccion=Atraccion;
			}
			else {
				boolean Noencontrado= true;
				int i=0;
				while (i<atraccionesCapacitadas.size() && Noencontrado) {
					Atraccion atracti= atraccionesCapacitadas.get(i);
					if (atracti.equals(Atraccion)) {
						Noencontrado= false;
						atraccion=Atraccion;
					}
					i++;
				}
				if (Noencontrado) {
					throw new Exception("El empleado no se encuentra capacitado para esta atracción");
				}
			}
		}
		else {
			atraccion=atract;
		}
		
	}
	
	public void agregarAtraccion(Atraccion Atraccion) {
		atraccionesCapacitadas.addLast(Atraccion);
	}
	
	public void sacarAtraccion() {
		atraccion=null;
	}
}
