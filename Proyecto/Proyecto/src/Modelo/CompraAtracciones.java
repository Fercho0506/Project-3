package Modelo;

import java.util.ArrayList;
import java.util.List;

import Tiquetes.FastPass;
import Tiquetes.Tiquete;
import Usuarios.Usuario;

public class CompraAtracciones extends Compra{
	private static final long serialVersionUID = 1L;
	private List<Tiquete> tiquetesAdquiridos;
	private List<FastPass> fastPassComprados;
	
	public CompraAtracciones(int codigo, Usuario comprador) {
		super(codigo, comprador, "atracciones");
		this.tiquetesAdquiridos= new ArrayList<Tiquete>();
		this.fastPassComprados= new ArrayList<FastPass>();
	}
	
	public List<Tiquete> getTiquetes(){
		return tiquetesAdquiridos;
	}
	
	public void agregarTiquete(Tiquete tiquete) {
		tiquetesAdquiridos.addLast(tiquete);
		agregarProducto(tiquete.getPrecio());
	}
	
	public void agregarFastPass(FastPass fast) {
		fastPassComprados.addLast(fast);
		agregarProducto(fast.getPrecio());
	}
	
	public List<FastPass> getFastPass(){
		return fastPassComprados;
	}
}
