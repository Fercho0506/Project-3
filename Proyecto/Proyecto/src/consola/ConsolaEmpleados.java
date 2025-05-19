package consola;

import java.util.Scanner;

import LugarServicios.Cafeteria;
import Modelo.Parque;
import Usuarios.Cajero;
import Usuarios.Cocinero;
import Usuarios.Empleado;
import Usuarios.EmpleadoAtracciones;
import Usuarios.EmpleadoServiciosgenerales;

public class ConsolaEmpleados extends ConsolaUsuario{
	private static Empleado empleado;
	
	public ConsolaEmpleados(Parque parque, Empleado empleado) {
		super(parque);
		ConsolaEmpleados.empleado=empleado;
	}
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- MENÚ EMPLEADO ---");
            System.out.println("1. Ver mi info");
            System.out.println("2. Ver mis turnos");
            System.out.println("3. Ver mi labor");
            System.out.println("4. Ver mi lugar de trabajo");
            System.out.println("5. Ir al menú de cliente");
            System.out.println("6. Cambiar contraseña");
            System.out.println("7. Salir al menú principal");
            System.out.print("Opción: ");
            try {
            	int opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
            case 1 -> verInfo();
            case 2 -> verTurnos();
            case 3 -> verLabor();
            case 4 -> verLugarTrabajo();
            case 5 -> {
            	new ConsolaClientes(parque, empleado);
    			System.out.println("\nBienvenido al menú de cliente\n");
    			ConsolaClientes.main(args);
            }
            case 6 ->{
            	new ConsolaCambiarInfo(empleado);
            	System.out.println("\nBienvenido al menú de cambio de contraseña\n");
            	ConsolaCambiarInfo.main(args);
            }
            case 7 -> {
                continuar = false;
            }
            default -> System.out.println("Opción no válida.");
            }
            }
            catch (Exception e) {
            	System.out.println("Escoja un número por favor");
            }
        }
        System.out.println("\n¡Lo esperamos pronto!\n");
	   
	}
	
	private static void verInfo() {
		System.out.println("\n--- TU INFORMACIÓN ---");
		System.out.println("\nUsted es un empleado: "+empleado.gettipo());
		System.out.println("\nSu usuario es: "+empleado.getUsuario());
		System.out.println("\nSu edad son: "+empleado.getEdad()+" años");
	}
	
	private static void verTurnos() {
		if(empleado.gettipo().equals("serviciosGenerales")) {
			System.out.println("\nUsted como empleado de servicios generales no tiene turnos asignados\n");
		}
		else if (empleado.getTurnos().size()>0) {
			for (String i: empleado.getTurnos()) {
				System.out.println("\nTiene el turno asignado de: "+i+"\n");
			}
		}
		else {
			System.out.println("\nEn este momento no tiene turnos asignados\n");
		}
	}
	
	private static void verLabor() {
		if (empleado.getLabor() != "") {
			System.out.println("\nSu labor del momento es: "+empleado.getLabor()+"\n");
		}
		else {
			System.out.println("\nEn este momento no una labor asignada\n");
		}
	}
	
	private static void verLugarTrabajo() {
		boolean tienelugar=true;
		if (empleado.gettipo().equals("serviciosGenerales")) {
			EmpleadoServiciosgenerales empleadoServi= (EmpleadoServiciosgenerales) empleado;
			if (empleadoServi.getLugar()!=null) {
				System.out.println("\nSu lugar de trabajo en este momento es en una: "+empleadoServi.getLugar().getTipo()+" "+empleadoServi.getLugar().getNombre()+"\n");
			}
			else {
				tienelugar=false;
			}
		}
		else if(empleado.gettipo().equals("cocinero")) {
			Cocinero cocinero= (Cocinero) empleado;
			Cafeteria cafeteria=cocinero.getCafeteria();
			if (cafeteria!=null) {
				System.out.println("\nSu lugar de trabajo en este momento es en la: cafetería"+" "+cafeteria.getNombre()+"\n");
			}
			else {
				tienelugar=false;
			}
		}
		else if(empleado.gettipo().equals("empleadoAtracciones")) {
			EmpleadoAtracciones empAtract= (EmpleadoAtracciones) empleado;
			if (empAtract.getAtraccion()!=null) {
				System.out.println("\nSu lugar de trabajo en este momento es en la atraccción: "+empAtract.getAtraccion().getNombre()+"\n");
			}
			else {
				tienelugar=false;
			}
		}
		else if(empleado.gettipo().equals("cajero")) {
			Cajero cajero= (Cajero) empleado;
			if (cajero.getAtraccion()!=null) {
				System.out.println("\nSu lugar de trabajo en este momento es en la atraccción: "+cajero.getAtraccion().getNombre()+"\n");
			}
			else if(cajero.getLugar()!=null) {
				System.out.println("\nSu lugar de trabajo en este momento es en la: "+ cajero.getLugar().getTipo()+" "+cajero.getLugar().getNombre()+"\n");
			}
			else {
				tienelugar=false;
			}
		}
		if(!tienelugar) {
			System.out.println("\nEn este momento no tiene ningún lugar de trabajo asignado\n");
		}
	}
}
