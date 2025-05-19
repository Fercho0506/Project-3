package Tiquetes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import Usuarios.Usuario;

public abstract class Tiquete implements Serializable {
    private static final long serialVersionUID = 1L;

	private String exclusividad;
	private String tipoTiquete;
	private boolean usado;
	private Usuario usuario;
	private int precio;
	private int codigo;
	private boolean impreso;
	private String fechaExpedicion;

	public Tiquete(String exclusividad, Usuario usuario, int precio, String tipo) {
		this.exclusividad=exclusividad;
		this.usuario= usuario;
		this.precio=precio;
		this.tipoTiquete=tipo;
		this.usado= false;
		this.codigo=generarCodigo();
		this.impreso=false;
		this.fechaExpedicion= generarFecha();
	}
	
	public String getExclusividad() {
		return exclusividad;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public float getPrecio() {
		return precio;
	}
	
	public boolean getUsado() {
		return usado;
	}
	
	public String getTipo() {
		return tipoTiquete;
	}
	
	public void setExcllusividad(String excl) {
		exclusividad=excl;
	}
	
	public void setPrecio(int price) {
		precio=price;
	}
	
	public String getFechaExpedicion() {
		return fechaExpedicion;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public boolean getImpreso() {
		return impreso;
	}
	
	public void imprimir() {
		this.impreso=true;
	}
	
	public void usarTiquete() {
		usado= true;
		usuario.UsarTiquete(this);
	}
	
	private int generarCodigo() {
		int numero = ( int ) ( Math.random( ) * 10e7 );
		return numero;
	}
	
	private String generarFecha() {
		Date fecha= new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		String dia = formato.format(fecha);
		return dia;
	}
	
	@Override
    public String toString( )
    {
        if (tipoTiquete.equals("entrada")) {
        	return "Entrada";
        }
        else {
        	return "Tiquete "+tipoTiquete;
        }
    }
}
