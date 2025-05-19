package Modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import Atracciones.Atraccion;
import Atracciones.AtraccionMecanica;
import Atracciones.Espectaculo;
import LugarServicios.LugarServicio;
import LugarServicios.Taquilla;
import Persistencia.Persistencia;
import Tiquetes.FastPass;
import Tiquetes.Tiquete;
import Tiquetes.TiqueteTemporada;
import Usuarios.Administrador;
import Usuarios.Cajero;
import Usuarios.Empleado;
import Usuarios.Usuario;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Parque implements Serializable {
    private static final long serialVersionUID = 1L;

	private String direccion;
	private String nombre;
	private int capacidad;
	private boolean abierto;
	private HashMap<String, Usuario> usuarios;
	private List<Atraccion> atracciones;
	private List<Espectaculo> espectaculos;
	private List<LugarServicio> lugaresServicios;
	
	public Parque(String direccion, String nombre, int capacidad) {
		this.direccion= direccion;
		this.nombre=nombre;
		this.capacidad=capacidad;
		this.abierto=false;
		this.usuarios= new HashMap<String, Usuario>();
		this.atracciones= new ArrayList<Atraccion>();
		this.espectaculos= new ArrayList<Espectaculo>();
		this.lugaresServicios= new ArrayList<LugarServicio>();
	}
	
	public void agregarUsuario(Usuario usuario) throws Exception{
		if (!usuarios.containsKey(usuario.getUsuario())) {
			usuarios.put(usuario.getUsuario(), usuario);
		}
		else {
			throw new Exception("\nYa hay un usuario con ese login, escoja otro login porfavor.\n");
		}
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public boolean getAbierto() {
		return abierto;
	}
	
	public void agregaratraccion(Atraccion atraccion) {
		atracciones.addLast(atraccion);
	}
	
	public void agregarEspectaculo(Espectaculo espectaculo) {
		espectaculos.addLast(espectaculo);
	}
	
	public void agregarLugar(LugarServicio lugar) {
		lugaresServicios.addLast(lugar);
	}
	
	public Collection<Usuario> getUsuarios(){
		return usuarios.values();
	}
	
	public List<Empleado> getEmpleados(){
		List<Empleado> empleados= new ArrayList<Empleado>();
		for(Usuario i: usuarios.values()) {
			if (i.getTipoUsuario().compareTo("empleado")==0) {
				empleados.addLast((Empleado)i);
			}
		}
		return empleados;
	}
	
	public Empleado getEmpleado(List<Empleado> empleados, int possicion) {
		return empleados.get(possicion);
	}
	
	public boolean usuarioAdministrador(Usuario usuario) {
		return usuario.getTipoUsuario().compareTo("administrador")== 0;
	}
	
	public boolean autenticarIngreso(String login, String password) {
		Usuario usuario= usuarios.get(login);
		if(usuario != null) {
			return password.equals(usuario.getPassword());
		}
		return false;
	}
	
	public Usuario getUsuario(String login) {
		return usuarios.get(login);
	}
	
	public List<Atraccion> getAtracciones(){
		return atracciones;
	}
	
	public List<Espectaculo> getEspectaculos(){
		return espectaculos;
	}
	
	public List<LugarServicio> getLugares(){
		return lugaresServicios;
	}
	
	public void setNombre(String Nombre) {
		nombre=Nombre;
	}
	
	public void setCapacidad(int capacity ) {
		capacidad=capacity;
	}
	
	public void setDireccion(String direct) {
		direccion=direct;
	}
	
	public void abrirParque() {
		abierto=true;
	}
	
	public void cerrarParque() {
		abierto=false;
	}
	
	public boolean eliminarUsuario(Usuario usuario) {
		return usuarios.remove(usuario.getUsuario(), usuario);
	}
	
	public void eliminarAtraccion(Atraccion atraccion) {
		int i=0;
		boolean noEncontrado= true;
		while(i<atracciones.size() && noEncontrado) {
			Atraccion atract= atracciones.get(i);
			if (atraccion.equals(atract)) {
				atracciones.remove(i);
				noEncontrado=false;
			}
			i++;
		}
	}
	
	public void eliminarEspectaculo(Espectaculo espectaculo) {
		int i=0;
		boolean noEncontrado= true;
		while(i<espectaculos.size() && noEncontrado) {
			Espectaculo espe= espectaculos.get(i);
			if (espe.equals(espectaculo)) {
				espectaculos.remove(i);
				noEncontrado=false;
			}
			i++;
		}
	}
	
	public void eliminarLugar(LugarServicio lugar) {
		int i=0;
		boolean noEncontrado= true;
		while(i<lugaresServicios.size() && noEncontrado) {
			LugarServicio place= lugaresServicios.get(i);
			if (lugar.equals(place)) {
				lugaresServicios.remove(i);
				noEncontrado=false;
			}
			i++;
		}
	}
	
	public void cargarData() {
		
	}
	
	public void salvarData() {
		
	}
	
	public void AsignarTurno(Empleado empleado, String turno, Usuario usuario) throws Exception{
		if (usuarioAdministrador(usuario)) {
			Administrador admin= (Administrador) usuario;
			admin.AsignarTurno(empleado,turno);
		}
		else {
			throw new Exception("Solo administradsores pueden realizar este trabajo");
		}
	}
	
	public void AsignarLabor(Empleado empleado, String labor, LugarServicio lugar, Usuario usuario) throws Exception{
		if (usuarioAdministrador(usuario)) {
			Administrador admin= (Administrador) usuario;
			admin.AsignarLabor(empleado,labor, lugar);
		}
		else {
			throw new Exception("Solo administradsores pueden realizar este trabajo");
		}
	}
	public void retirarTurnoEmpleado(Empleado empleado, String turno, Usuario usuario) throws Exception {
		if (usuarioAdministrador(usuario)) {
			Administrador admin= (Administrador) usuario;
			admin.retirarTurnoEmpleado(empleado, turno);
		}
		else {
			throw new Exception("Solo administradsores pueden realizar este trabajo");
		}
	}
	
	public void asignarLugarEmpleado(Empleado empleado, LugarServicio lugar, Usuario usuario) throws Exception {
		if (usuarioAdministrador(usuario)) {
			Administrador admin= (Administrador) usuario;
			admin.asignarLugarEmpleado(empleado, lugar);
		}
		else {
			throw new Exception("Solo administradsores pueden realizar este trabajo");
		}
	}
	
	public void asignarAtraccionEmpleado(Empleado empleado, AtraccionMecanica atraccion, Usuario usuario) throws Exception{
		if (usuarioAdministrador(usuario)) {
			Administrador admin= (Administrador) usuario;
			admin.asignarAtraccionEmpleado(empleado, atraccion);
		}
		else {
			throw new Exception("Solo administradsores pueden realizar este trabajo");
		}
	}
	
	public int generarCodigoCompra() {
		int numero = ( int ) ( Math.random( ) * 10e7 );
		return numero;
	}
	
	public CompraAtracciones registrarCompraTiquetesOnline(Usuario usuario, List<Tiquete> tiquetes, List<FastPass> fast) {
		float precio= calcularPrecioCompraTiquetes(usuario, tiquetes, fast);
		CompraAtracciones compra= new CompraAtracciones(generarCodigoCompra(), usuario);
		for (Tiquete i: tiquetes) {
			usuario.AgregarTiquete(i);
			compra.agregarTiquete(i);
		}
		for(FastPass a: fast) {
			usuario.agregarFastPass(a);
			compra.agregarFastPass(a);
		}
		compra.setPrecio(precio);
		usuario.agregarCompra(compra);
		return compra;
		
	}
	
	public float calcularPrecioCompraTiquetes(Usuario usuario, List<Tiquete> tiquetes, List<FastPass> fast) {
		float precio=0;
		if (usuario.getTipoUsuario().equals("cliente")){
			for (Tiquete i: tiquetes) {
				precio += i.getPrecio();
			}
			for(FastPass a: fast) {
				precio += a.getPrecio();
			}
		}
		else {
			for (Tiquete i: tiquetes) {
				precio += i.getPrecio()*0.8;
			}
			for(FastPass a: fast) {
				precio += a.getPrecio()*0.8;
			}
		}
		return precio;
	}
	
	public void registrarCompraTiquetesTaquilla(Usuario usuario, List<Tiquete> tiquetes, List<FastPass> fast, 
			Taquilla taquilla) throws Exception{
		if (taquilla.getCajero()!=null) {
			CompraAtracciones compra=registrarCompraTiquetesOnline(usuario, tiquetes, fast);
			taquilla.registrarVenta(compra);
		}
		else {
			throw new Exception("La taquilla no tiene un cajero que registre la venta");
		}
	}
	
	public void IngresarAtraccion(Atraccion atraccion, Tiquete tiquete) throws Exception{
		if (atraccion.getCajero() != null && atraccion.getEmpleadosMin() <= atraccion.getNumeroEmpleados()) {
			Cajero cajero= atraccion.getCajero();
			boolean sePuede= cajero.validarTiquete(tiquete);
			if (atraccion.getAbierta()) {
				if (sePuede) {
					System.out.print("\nEl ingreso fue exitoso\n");
				}
				else {
					throw new Exception("el cliente no puede ingresar a la atracción");
				}
			}
			else {
				throw new Exception("La atracción se encuentra cerrada");
			}
		}
		else {
			throw new Exception("La atracción no tiene un cajero o empleados suficientes");
		}
	}
	
	public void realizarOtraCompra(CompraServicio compra) {
		compra.getComprador().agregarCompra(compra);
		compra.getLugar().registrarVenta(compra);
	}
	
	public boolean registrarEntrada(Tiquete tiquete, String fechaHoy, List<FastPass> fast) {
		for (FastPass i: fast) {
			if(fechaHoy != i.getDia()) {
				i.usar();
			}
		}
		SimpleDateFormat formato =new SimpleDateFormat("dd-MM-yyyy");
		Date fecha2;
		
		if (tiquete.getTipo().compareTo("entrada") != 0) {
			if(tiquete.getTipo().compareTo("temporada") == 0) {
				TiqueteTemporada tiquet= (TiqueteTemporada) tiquete;
				String fechaFin = tiquet.getFin()+"-2025";
				String fechaIni = tiquet.getInicio()+"-2025";
				try {
		            Date fecha1 = formato.parse(fechaFin);
		            Date fecha3 =formato.parse(fechaIni);
		            fecha2 = formato.parse(fechaHoy);
		            int num = fecha1.compareTo(fecha2);
		            if (num<0) {
		            	tiquet.usarTiquete();
		            	return false;
		            }
		            num= fecha3.compareTo(fecha2);
		            if (num>0) {
		            	return false;
		            }
		            return true;
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }
			}
			else {
				Date fechaTicket;
				try {
					fecha2 = formato.parse(fechaHoy);
					fechaTicket = formato.parse(tiquete.getFechaExpedicion());
					if (!fecha2.equals(fechaTicket)) {
						tiquete.usarTiquete();
					}
					return (!tiquete.getUsado());
				} catch (ParseException e) {
				}
			}
		}
		return false;
	}
	
	public void registrarSalida(Tiquete tiquete, String fechaHoy, List<FastPass> fast) {
		for (FastPass i: fast) {
			i.usar();
		}
		if (tiquete.getTipo().compareTo("regular")==0) {
			tiquete.usarTiquete();
		}
		else if(tiquete.getTipo().compareTo("temporada")==0) {
			TiqueteTemporada tiquet= (TiqueteTemporada) tiquete;
			String fechaFin = tiquet.getFin()+"-2025";
			SimpleDateFormat formato =new SimpleDateFormat("dd-MM-yyyy");
			try {
	            Date fecha1 = formato.parse(fechaFin);
	            Date fecha2 = formato.parse(fechaHoy);

	            int num = fecha1.compareTo(fecha2);
	            if (num==0) {
	            	tiquet.usarTiquete();
	            }
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
		}
	}
	
	public void cargarData1() {
	    Parque parqueCargado = (Parque) Persistencia.cargarObjeto("parque.bin");
	    if (parqueCargado != null) {
	        this.direccion = parqueCargado.getDireccion();
	        this.nombre = parqueCargado.getNombre();
	        this.capacidad = parqueCargado.getCapacidad();
	        this.abierto = parqueCargado.getAbierto();
	        this.usuarios = new HashMap<>();
	        this.usuarios.putAll(parqueCargado.usuarios);
	        this.atracciones = new ArrayList<>(parqueCargado.atracciones);
	        this.espectaculos = new ArrayList<>(parqueCargado.espectaculos);
	        this.lugaresServicios = new ArrayList<>(parqueCargado.lugaresServicios);
	    }
	}

	public void salvarData1() {
	    Persistencia.guardarObjeto(this, "parque.bin");
	}

	
}
