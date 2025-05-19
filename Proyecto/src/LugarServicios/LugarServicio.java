package LugarServicios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Modelo.Compra;
import Usuarios.Empleado;

public class LugarServicio implements Serializable {
    private static final long serialVersionUID = 1L;
	private List<Empleado> empleados;
	private String nombre;
	private Empleado cajero;
	private String tipoLugar;
	private int ventas;
	private List<Compra> historialCompras;
	
	public LugarServicio(String tipo, String nombre) {
		this.tipoLugar=tipo;
		this.nombre=nombre;
		this.ventas=0;
		this.empleados= new ArrayList<Empleado>();
		this.historialCompras= new ArrayList<Compra>();
		this.cajero=null;
	}
	
	public String getTipo() {
		return tipoLugar;
	}
	
	public int getVentas() {
		return ventas;
	}
	
	public Empleado getCajero() {
		return cajero;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setVentas(int venta) {
		ventas=venta;
	}
	
	public void setCajero(Empleado cajer) {
		cajero=cajer;
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public void sacarCajero() {
		cajero=null;
	}
	
	public void registrarVenta(Compra compra) {
		historialCompras.addLast(compra);
		ventas+= compra.getPrecio();
	}
	
	public void agregarEmpleado(Empleado empleado) {
		empleados.addLast(empleado);
	}
	
	public void eliminarEmpleado(Empleado empleado) {
		int i=0;
		boolean noEncontrado= true;
		while(i<empleados.size() && noEncontrado) {
			Empleado employ= empleados.get(i);
			if (employ.equals(empleado)) {
				empleados.remove(i);
				noEncontrado=false;
			}
			i++;
		}
	}
}
