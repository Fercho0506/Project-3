package consola;

import java.util.Scanner;

import Usuarios.Usuario;

public class ConsolaCambiarInfo {
	private static Usuario usuario;
	
	public ConsolaCambiarInfo(Usuario usuario) {
		ConsolaCambiarInfo.usuario=usuario;
	}
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		boolean continuar = true;

        while (continuar) {
        	System.out.println("\n--- MENÚ CAMBIAR CONTRASEÑA ---");
            System.out.println("1. Cambiar contraseña");
            System.out.println("2. Ir al menú anterior");
            System.out.print("Opción: ");
            try {
            	int opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
            case 1 -> cambiarPassword(scanner);
            case 2 -> {
            	continuar=false;
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
	
	private static boolean aceptarCambio(Scanner scanner) {
		boolean continuar = true;
        while (continuar) {
        	System.out.println("\n--- ¿ESTA SEGURO? ---");
            System.out.println("1. si");
            System.out.println("2. no");
            System.out.print("Opción: ");
            try {
            	int opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
            case 1 -> {
            	return true;
            	}
            case 2 -> {
            	return false;
            }
            
            default -> System.out.println("Opción no válida.");
            }
            }
            catch (Exception e) {
            	System.out.println("Escoja un número por favor");
            }
        }
		return continuar;
	}
	

	private static void cambiarPassword(Scanner scanner) {
		System.out.println("\nSu contraseña establecida es: "+usuario.getPassword()+"\n");
		boolean continuar = true;

        while (continuar) {
        	System.out.println("\n--- ¿DESEA CAMBIAR SU CONTRASEÑA ACTUAL? ---");
            System.out.println("1. si");
            System.out.println("2. no");
            System.out.print("Opción: ");
            try {
            	int opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
            case 1 -> {
            	System.out.println("\nIngrese la nueva contraseña que desea: ");
            	String password= scanner.nextLine();
            	System.out.println("\nEsta seguro de cambiar la contraseña de "+usuario.getPassword()+" por "+password+"?");
            	boolean resultado= aceptarCambio(scanner);
            	if (resultado) {
            		usuario.setPassword(password);
            		System.out.println("\nSu contraseña fue modificada con éxito..\n");
            		continuar=false;
            	}
            	else {
            		System.out.println("\nPerfecto, no se modificará su contraseña de momento.\n");
            	}
            }
            case 2 -> {
            	System.out.println("\nPerfecto, no se modificó su contraseña.\n");
            	continuar=false;
            }
            
            default -> System.out.println("Opción no válida.");
            }
            }
            catch (Exception e) {
            	System.out.println("Escoja un número por favor");
            }
        }
	}
	
}
