package Atracciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Usuarios.Cajero;
import Usuarios.Empleado;
import Usuarios.EmpleadoAtracciones;

public abstract class Atraccion implements Serializable {
    private static final long serialVersionUID = 1L;
	
	private String nombre;
	private int capacidad;
	private String ubicacion;
	private int empleadosMin;
	private String exclusividad;
	private Date temporada;
	private boolean abierta;
	private String tipo;
	private Cajero cajero;
	private List<EmpleadoAtracciones> empleados;
	
	public Atraccion(String nombre, int capacidad, String ubicacion, int empleadosMin, String exclusividad, Date temporada, String tipo) {
		this.nombre=nombre;
		this.capacidad=capacidad;
		this.ubicacion= ubicacion;
		this.empleadosMin= empleadosMin;
		this.exclusividad=exclusividad;
		this.temporada=temporada;
		this.tipo=tipo;
		this.abierta = false;
		this.cajero=null;
		this.empleados= new ArrayList<EmpleadoAtracciones> ();
		
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Cajero getCajero() {
		return cajero;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public List<EmpleadoAtracciones> getEmpleados(){
		return empleados;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public EmpleadoAtracciones getEmpleado(int posicion) {
		return empleados.get(posicion);
	}
	
	public int getEmpleadosMin() {
		return empleadosMin;
	}
	
	public String getExclusividad() {
		return exclusividad;
	}
	
	public Date getTemporada() {
		return temporada;
	}
	
	public boolean getAbierta() {
		return abierta;
	}
	
	public int getNumeroEmpleados() {
		return empleados.size();
	}
	
	public void setNombre(String name) {
		nombre=name;
	}
	
	public void setCajero(Cajero cajer) {
		cajero=cajer;
	}
	
	public void setCapacidad(int capa) {
		capacidad= capa;
	}
	
	public void getUbicacion(String ubi) {
		ubicacion=ubi;
	}
	
	public void getEmpleadosMin(int emp) {
		empleadosMin= emp;
	}
	
	public void getExclusividad(String exclu) {
		exclusividad= exclu;
	}
	
	public void getTemporada(Date temp) {
		temporada=temp;
	}
	
	public void agregarEmpleado(EmpleadoAtracciones empleado) {
		empleados.addLast(empleado);
	}
	
	public void abrirAtraccion() {
		abierta=true;
	}
	
	public void cerrarAtraccion() {
		abierta=false;
	}
	
	public void sacarCajero() {
		cajero=null;
	}
	
	public void EliminarEmpleado(Empleado empleado) {
		int i=0;
		boolean NoEncontrado= true;
		while(i<empleados.size() && NoEncontrado) {
			EmpleadoAtracciones employ= empleados.get(i);
			if (employ.equals(empleado)){
				empleados.remove(i);
				NoEncontrado= false;
			}
			i++;
		}
	}
	
	@Override
    public String toString( )
    {
        return nombre;
    }

	public abstract void setTipo(String nuevoTipo);
}
