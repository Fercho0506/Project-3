package Atracciones;

import java.util.Date;

public class AtraccionCultural extends Atraccion{
	private static final long serialVersionUID = 1L;
	private int edadMin;
	
	public AtraccionCultural(String nombre, int capacidad, String ubicacion, int empleadosMin, String exclusividad, Date temporada,
			int edadMin){
		super(nombre, capacidad, ubicacion, empleadosMin, exclusividad, temporada, "cultural");
		this.edadMin=edadMin;
	}
	
	public int getEdadMin() {
		return edadMin;
	}
	
	public void setEdadMin(int edad) {
		edadMin=edad;
	}
}
