package Tiquetes;

import Usuarios.Usuario;

public class TiqueteRegular extends Tiquete {
    private static final long serialVersionUID = 1L;

	public TiqueteRegular(String exclusividad, Usuario usuario, int precio) {
		super(exclusividad, usuario, precio, "regular");
	}
}
