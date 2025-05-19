package Tiquetes;

import java.io.Serializable;

import Usuarios.Usuario;

public class FastPass implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String dia;
	private Usuario cliente;
	private float precio;
	private boolean valido;
	
	public FastPass(String dia, Usuario cliente, int precio) {
		this.dia=dia;
		this.cliente=cliente;
		this.precio=precio;
		this.valido=true;
	}
	
	public String getDia() {
		return dia;
	}
	
	public boolean getValido() {
		return valido;
	}
	
	public Usuario getCliente() {
		return cliente;
	}
	
	public float getPrecio() {
		return precio;
	}
	
	public void setPrecio(float price) {
		precio=price;
	}
	
	public void usar() {
		valido=false;
		cliente.usarFastPass(this);
	}
}
