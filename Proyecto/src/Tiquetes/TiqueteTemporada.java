package Tiquetes;

import Usuarios.Usuario;

public class TiqueteTemporada extends Tiquete {
    private static final long serialVersionUID = 1L;
	
	private String inicio;
	private String fin;
	
	public TiqueteTemporada(String exclusividad, Usuario usuario, int precio, String inicio, String fin) {
		super(exclusividad, usuario, precio, "temporada");
		this.inicio=inicio;
		this.fin=fin;
	}
	
	public String getInicio() {
		return inicio;
	}
	
	public String getFin() {
		return fin;
	}
}
