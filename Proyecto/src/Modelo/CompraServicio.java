package Modelo;

import java.util.ArrayList;
import java.util.List;

import LugarServicios.LugarServicio;
import Usuarios.Usuario;

public class CompraServicio extends Compra{
	private static final long serialVersionUID = 1L;
	private List<String> productos;
	private LugarServicio lugar;
	
	public CompraServicio(int codigo, Usuario comprador, LugarServicio lugar) {
		super(codigo, comprador, "servicios");
		this.lugar=lugar;
		this.productos= new ArrayList<String>();
	}
	
	public LugarServicio getLugar() {
		return lugar;
	}
	
	public List<String> getComprar(){
		return productos;
	}
	
	public void agregarProducto(String producto, int precio) {
		productos.addLast(producto);
		agregarProducto(precio);
	}
}
