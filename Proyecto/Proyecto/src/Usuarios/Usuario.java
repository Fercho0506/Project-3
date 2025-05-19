package Usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Modelo.Compra;
import Tiquetes.FastPass;
import Tiquetes.Tiquete;

public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
	private String login;
	private String password;
	private String tipoUsuario;
	private List<Tiquete> tiquetesUsar;
	private List<Tiquete> tiquetesUsados;
	private List<Compra> historialCompra;
	private List<FastPass> fastPasses;
	private List<FastPass> fastPassesUsados;
	private int edad;
	private float altura;
	
	public Usuario(String login, String password, String tipo, int edad, float altura) {
		this.login=login;
		this.password=password;
		this.tipoUsuario=tipo;
		this.tiquetesUsar= new ArrayList<Tiquete>();
		this.tiquetesUsados= new ArrayList<Tiquete>();
		this.historialCompra= new ArrayList<Compra>();
		this.fastPasses= new ArrayList<FastPass>();
		this.fastPassesUsados= new ArrayList<FastPass>();
		this.edad=edad;
		this.altura=altura;
		
	}
	
	public String getUsuario() {
		return login;
	}
	
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public float getAltura() {
		return altura;
	}
	
	public List<Tiquete> getTiquetesPorUsar(){
		return tiquetesUsar;
	}
	
	public List<Tiquete> getTiquetesPorUsados(){
		return tiquetesUsados;
	}
	
	public List<FastPass> getFastPassPorUsar(){
		return fastPasses;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public List<Compra> getHistorialCompras(){
		return historialCompra;
	}
	
	public void setTipoUsuario(String tipo) {
		this.tipoUsuario=tipo;
	}
	
	public void AgregarTiquete(Tiquete tiquete) {
		tiquetesUsar.addLast(tiquete);
	}
	public void UsarTiquete(Tiquete tiquete) {
		tiquetesUsados.addLast(tiquete);
		int i=0;
		boolean noEncontrado= true;
		while(i<tiquetesUsar.size() && noEncontrado) {
			Tiquete ticket= tiquetesUsar.get(i);
			if (tiquete.equals(ticket)) {
				tiquetesUsar.remove(i);
				noEncontrado=false;
			}
			i++;
		}
	}
	
	public void agregarCompra(Compra compra) {
		historialCompra.addLast(compra);
	}
	
	public void agregarFastPass(FastPass fast) {
		fastPasses.addLast(fast);
	}
	
	public void usarFastPass(FastPass fast) {
		fastPassesUsados.addLast(fast);
		int i=0;
		boolean noEncontrado= true;
		while(i<fastPasses.size() && noEncontrado) {
			FastPass fastPass= fastPasses.get(i);
			if (fast.equals(fastPass)) {
				fastPasses.remove(i);
				noEncontrado=false;
			}
			i++;
		}
	}
}
