package consola;

import Modelo.Parque;

public abstract class ConsolaUsuario {
	protected static Parque parque;
	
	public ConsolaUsuario(Parque parque) {
		ConsolaUsuario.parque=parque;
	}
}
