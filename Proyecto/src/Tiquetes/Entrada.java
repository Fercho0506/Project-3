package Tiquetes;

import Atracciones.Atraccion;
import Usuarios.Usuario;

public class Entrada extends Tiquete{
	private static final long serialVersionUID = 1L;
	private Atraccion atraccion;
	
	public Entrada(Usuario usuario, int precio, Atraccion atraccion) {
		super("N/A", usuario, precio, "entrada");
		this.atraccion= atraccion;
	}
	
	public Atraccion getAtraccion() {
		return atraccion;
	}
}
