package Usuarios;

import Atracciones.Atraccion;
import LugarServicios.Cafeteria;
import LugarServicios.LugarServicio;

public class Administrador extends Usuario{
	private static final long serialVersionUID = 1L;
	public Administrador(String login, String password, int edad, float altura) {
		super(login, password, "administrador", edad, altura);
	}
	
	public void AsignarTurno(Empleado empleado, String turno) throws Exception{
		if (!empleado.gettipo().equals("serviciosGenerales")) {
			empleado.AsignarTurno(turno);
		}
		else {
			throw new Exception ("Los empleados de servicios generales no tienen turno");
		}
	}
	
	public void AsignarLabor(Empleado empleado, String labor, LugarServicio lugar) throws Exception{
		if (labor.equals("cocina") && !empleado.gettipo().equals("cocinero")) {
			throw new Exception("Solo los cocineros pueden estar en la cocina");
		}
		else if(labor.equals("caja") && (!empleado.gettipo().equals("cajero") && !empleado.gettipo().equals("cocinero")) ){
		    throw new Exception("Solo cocineros o cajeros pueden estar en caja");
		}

		else if (labor.equals("atraccion") && !empleado.gettipo().equals("empleadoAtracciones")) {
			throw new Exception("Solo los empleados de atracciones pueden estar acargo de estas");
		}
		else if (labor.equals("caja")&& empleado.gettipo().equals("cocinero")) {
			if (lugar.getTipo().equals("cafeteria")) {
				Cafeteria cafe= (Cafeteria) lugar;
				cafe.setCajero(empleado);
				empleado.setLabor(labor);
			}
			else {
				throw new Exception("Los cocineros solo pueden trabajar en las cafeterias");
			}
		}
		else if(labor.equals("limpieza") && !empleado.gettipo().equals("serviciosGenerales")) {
			throw new Exception("Solo los empleados de servicios generales tienen tareas de limpieza");
		}
		else {
			empleado.setLabor(labor);
		}
	}
	public void retirarTurnoEmpleado(Empleado empleado, String turno) throws Exception {
		if (!empleado.gettipo().equals("serviciosGenerales")) {
			empleado.RetirarTurno(turno);
		}
		else {
			throw new Exception("Los empleados de servicios generales no tienen turnos.");
		}
	}
	
	public void asignarLugarEmpleado(Empleado empleado, LugarServicio lugar) throws Exception {
		if (empleado.gettipo().equals("empleadoAtracciones")) {
			throw new Exception("Este empleado es de atracciones, no lugar de servicio");
		}
		else if (empleado.gettipo().equals("cajero")){
			Cajero cajero= (Cajero)empleado;
			cajero.setLugar(lugar);
			lugar.setCajero(cajero);
		}
		else if (empleado.gettipo().equals("serviciosGenerales")){
			EmpleadoServiciosgenerales employ= (EmpleadoServiciosgenerales)empleado;
			employ.setLugar(lugar);
			lugar.agregarEmpleado(empleado);
		}
		else if(empleado.gettipo().equals("cocinero") && lugar.getTipo().equals("cafeteria")) {
			Cafeteria cafe= (Cafeteria) lugar;
			Cocinero coci= (Cocinero) empleado;
			cafe.setCocinero(coci);
			coci.setCafeteria(cafe);
		}
		else {
			throw new Exception("No se pudo asignar sitio");
		}
	}
	
	public void asignarAtraccionEmpleado(Empleado empleado, Atraccion atraccion) throws Exception{
		if (empleado.gettipo().equals("empleadoAtracciones")) {
			EmpleadoAtracciones employ= (EmpleadoAtracciones) empleado;
			employ.setAtraccion(atraccion);
			atraccion.agregarEmpleado(employ);
			
		}
		else if (empleado.gettipo().equals("cajero")){
			Cajero cajero= (Cajero)empleado;
			cajero.setAtraccion(atraccion);
			atraccion.setCajero(cajero);
		}
		else {
			throw new Exception("No se pudo asignar sitio");
		}
	}
	
	public void retirarLabor(Empleado empleado){
		empleado.setLabor("");
	}
	
	public void retirarLugarEmpleado(Empleado empleado) throws Exception{
		boolean noTenia= true;
		if (empleado.gettipo().equals("serviciosGenerales")) {
			EmpleadoServiciosgenerales servi= (EmpleadoServiciosgenerales) empleado;
			if (servi.getLugar()!=null) {
				servi.setLugar(null);
			}
			else {
				noTenia=false;
			}
		}
		else if (empleado.gettipo().equals("empleadoAtracciones")) {
			EmpleadoAtracciones atra= (EmpleadoAtracciones) empleado;
			if (atra.getAtraccion()!=null) {
				atra.sacarAtraccion();
			}
			else {
				noTenia=false;
			}
		}
		else if (empleado.gettipo().equals("cajero")) {
			Cajero cajero= (Cajero) empleado;
			if (cajero.getAtraccion()!=null) {
				cajero.setAtraccion(null);
			}
			else if (cajero.getLugar()!=null) {
				cajero.setLugar(null);
			}
			else {
				noTenia=false;
			}
		}
		else {
			Cocinero cocinero= (Cocinero) empleado;
			if(cocinero.getCafeteria()!=null) {
				cocinero.setCafeteria(null);
			}
			else {
				noTenia=false;
			}
		}
		if (!noTenia) {
			throw new Exception("El empleado ya se encontraba sin un lugar asignado");
		}
	}
}
