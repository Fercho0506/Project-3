package Modelo;

import java.io.Serializable;

import Usuarios.Usuario;

public abstract class Compra implements Serializable{
	private static final long serialVersionUID = 1L;
	private float precio;
	private int codigo;
	private Usuario comprador;
	private String tipo;
	
	public Compra(int codigo, Usuario comprador, String tipo) {
		this.precio=0;
		this.codigo=codigo;
		this.comprador=comprador;
		this.tipo=tipo;
	}
	
	public float getPrecio() {
		return precio;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public Usuario getComprador() {
		return comprador;
	}
	
	public void setPrecio(float i) {
		precio=i;
	}
	
	public void agregarProducto(float i) {
		precio +=i;
	}
	
	@Override
    public String toString( )
    {
        return "Compra "+tipo;
    }
}
