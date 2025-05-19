package Atracciones;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Espectaculo implements Serializable {
    private static final long serialVersionUID = 1L;
	private String nombre;
	private Date fecha;
	private String horario;
	private boolean abierto;
	
	public Espectaculo(String nombre, Date fecha, String horario) {
		this.nombre=nombre;
		this.fecha=fecha;
		this.horario=horario;
		this.abierto=false;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Date getfecha() {
		return fecha;
	}
	
	public String getHorario() {
		return horario;
	}
	
	public boolean getAbierto() {
		return abierto;
	}
	
	public void setNombre(String name) {
		nombre=name;
	}
	
	public void setFecha(Date date) {
		fecha=date;
	}
	
	public void setHorario(String hor) {
		horario=hor;
	}
	
	public void abrir() {
		abierto=true;
	}
	
	public void cerrar() {
		abierto=false;
	}
	
	public boolean verificarFecha() {
		Date fechaHoy= new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		String hoy = formato.format(fechaHoy);
		String dia= formato.format(fecha);
		if(hoy.equals(dia)) {
			return true;
		}
		else {
			cerrar();
			return false;
		}
	}
}
