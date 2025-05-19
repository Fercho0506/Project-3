package LugarServicios;

import java.util.Collection;
import java.util.HashMap;

import Usuarios.Cocinero;


public class Cafeteria extends LugarServicio{
	private static final long serialVersionUID = 1L;
	private HashMap<String, Integer> productos;
	private Cocinero cocinero;
	
	public Cafeteria(String nombre) {
		super("cafeteria", nombre);
		this.productos= new HashMap<String, Integer>();
		this.cocinero=null;
	}
	
	public void agregarProducto(String nombre, int precio) {
		productos.put(nombre, precio);
	}
	
	public Cocinero getCocinero() {
		return cocinero;
	}
	
	public void setCocinero(Cocinero cociner) {
		cocinero=cociner;
	}
	
	public void quitarProducto(String nombre) {
		productos.remove(nombre, nombre);
	}
	
	public int getPrecioProducto(String producto) {
		return productos.get(producto);
	}
	
	public Collection<String> getProductos(){
		return productos.keySet();
	}
	
}
