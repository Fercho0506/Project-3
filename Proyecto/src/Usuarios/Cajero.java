package Usuarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Atracciones.Atraccion;
import Atracciones.AtraccionCultural;
import Atracciones.AtraccionMecanica;
import LugarServicios.LugarServicio;
import Tiquetes.Entrada;
import Tiquetes.Tiquete;
import Tiquetes.TiqueteTemporada;

public class Cajero extends Empleado{
	private static final long serialVersionUID = 1L;
	private Atraccion atraccion;
	private LugarServicio lugar;
	
	public Cajero(String login, String password, int edad, float altura, 
			String labor) {
		super(login, password, edad, altura, "cajero", labor);
		this.atraccion=null;
		this.lugar=null;
	}
	
	public Atraccion getAtraccion() {
		return atraccion;
	}
	
	public LugarServicio getLugar() {
		return lugar;
	}
	
	public void setAtraccion(Atraccion Atraccion) {
		this.atraccion= Atraccion;
		this.lugar= null;
	}
	
	public void setLugar(LugarServicio lugar) {
		this.atraccion= null;
		this.lugar= lugar;
	}
	
	public boolean validarTiquete(Tiquete tiquete) {
		if (atraccion.getTipo().equals("mecanica")){
			AtraccionMecanica atract= (AtraccionMecanica)atraccion;
			if (tiquete.getUsuario().getAltura()<atract.getAlturaMax() && 
					tiquete.getUsuario().getAltura()>atract.getAlturaMin()) {
				if (!tiquete.getUsado()) {
					if(tiquete.getTipo().equals("temporada")) {
						return validarTemporada(tiquete);
					}
					if (tiquete.getTipo().equals("entrada")) {
						return validarEntrada(tiquete);
					}
					else if (tiquete.getTipo().equals("regular")) {
						SimpleDateFormat formato =new SimpleDateFormat("dd-MM-yyyy");
						String fechaHoy= formato.format(new Date());
						if (tiquete.getFechaExpedicion().equals(fechaHoy)) {
							return validarExclusividad(tiquete);
						}
						else {
							tiquete.usarTiquete();
							return false;
						}
					}
				}
			}
		}
		else {
			AtraccionCultural atract= (AtraccionCultural)atraccion;
			if (atract.getEdadMin()<= tiquete.getUsuario().getEdad() && !tiquete.getUsado()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean validarTemporada(Tiquete tiquete) {
		Date fecha= new Date();
    	SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    	String fechaHoy= formato.format(fecha);
		TiqueteTemporada tiquet= (TiqueteTemporada) tiquete;
		String fechaFin = tiquet.getFin()+"-2025";
		String fechaIni = tiquet.getInicio()+"-2025";
		try {
            Date fecha1 = formato.parse(fechaFin);
            Date fecha2 = formato.parse(fechaHoy);
            Date fecha3 =formato.parse(fechaIni);

            int num = fecha1.compareTo(fecha2);
            if (num<0) {
            	tiquet.usarTiquete();
            	System.out.print("\nSu tiquete de temporada ya no es válido\n");
            	return false;
            }
            num= fecha3.compareTo(fecha2);
            if (num>0) {
            	System.out.print("\nSu tiquete de temporada todavía no se puede usar\n");
            	return false;
            }
            return validarExclusividad(tiquet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public boolean validarEntrada(Tiquete tiquete) {
		Entrada ticket= (Entrada) tiquete;
		SimpleDateFormat formato =new SimpleDateFormat("dd-MM-yyyy");
		String fechaHoy= formato.format(new Date());
		if (ticket.getAtraccion().equals(atraccion) && ticket.getFechaExpedicion().equals(fechaHoy)){
			ticket.usarTiquete();
			return true;
		}
		ticket.usarTiquete();
		return false;
	}
	
	public boolean validarExclusividad(Tiquete tiquete) {
		String exclusividad= tiquete.getExclusividad();
		if (atraccion.getExclusividad().equals(exclusividad)) {
			return true;
		}
		else if (exclusividad.equals("diamante")) {
			return true;
		}
		else if(exclusividad.equals("oro") && atraccion.getExclusividad().equals("familiar")){
			return true;
		}
		return false;		
	}
	
}
