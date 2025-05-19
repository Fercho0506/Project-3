package consola;

import java.util.Scanner;
import Modelo.Parque;
import Persistencia.Persistencia;
import Usuarios.Administrador;
import Usuarios.Cliente;
import Usuarios.Empleado;
import Usuarios.Usuario;

public class InterfazPrincipal {
	private static Parque parque;
    private static final String RUTA_ARCHIVO = "data/parque.bin";
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        // Intentamos cargar parque
        try {
            parque = (Parque) Persistencia.cargarObjeto(RUTA_ARCHIVO);
            System.out.println("Parque cargado desde archivo.");
            
            // Validación extra por si cargarObjeto devuelve null
            if (parque == null) {
                System.out.println("⚠️ El archivo fue leído, pero el objeto Parque es null. Se creará uno nuevo.");
                parque = new Parque("Calle 123", "Diversiones S.A", 500);
            }  
        } catch (Exception e) {
            System.out.println("⚠️ Error al cargar el parque. Se creará uno nuevo.");
            e.printStackTrace();  // 🔍 Esto mostrará el error específico
            parque = new Parque("Calle 123", "Diversiones S.A", 500);
        }
        
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Autenticarse");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir y guardar");
            System.out.print("Opción: ");
            try {
            	int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1 -> {
                    	autenticarse(scanner, continuar, args);
                    }
                    case 2 -> {
                    	int edad=0;
                    	float altura=0;
                    	System.out.println("\nIngrese un login: ");
                    	String login= scanner.nextLine().strip();
                    	System.out.println("\nIngrese la contraseña que desea: ");
                    	String password= scanner.nextLine().strip();
                    	System.out.println("\nIngrese su edad (ej: 20): ");
                    	try {
                    		edad = Integer.parseInt(scanner.nextLine());
                    	}catch(Exception e) {
                        	System.out.println("\nPor favor ingrese el número de su edad correctamente\n");
                    	}
                    	try {
                    		System.out.println("\nIngrese su estatura (ej: 1.75): ");
                    		altura=Float.parseFloat(scanner.nextLine());
                        	Cliente c1= new Cliente(login, password, edad, altura);
                        	try {
                        		parque.agregarUsuario(c1);
                            	System.out.println("\nSu registro a la plataforma como cliente fue exitoso!\n");
                        	}catch (Exception o) {
                        		System.out.println(o.getMessage());
                        	}
                        	
                    	} catch(Exception a) {
                    		System.out.println("\nPor favor ingrese su altura correctamente\n");
                    	}
                    }
                    case 3 -> {
                        Persistencia.guardarObjeto(parque, RUTA_ARCHIVO);
                        continuar = false;
                        System.out.println("¡Gracias por usar la app!");
                        scanner.close();
                    }
                    default -> System.out.println("Opción no válida.");
                }
            }catch(Exception e) {
            	System.out.println("\nPor favor escoja un número como opción\n");
            }
            
        }
    }
    
    private static void autenticarse(Scanner scanner, boolean continuar, String[] args) {
    	System.out.println("\nIngrese su usuario: ");
    	String login= scanner.nextLine().strip();
    	System.out.println("\nIngrese su contraseña: ");
    	String password= scanner.nextLine().strip();
    	boolean valido= parque.autenticarIngreso(login, password);
    	if (valido) {
    		Usuario usuario= parque.getUsuario(login);
    		System.out.println("\nSu autenticación fue correcta\n");
    		System.out.println("Usted es un tipo de usuario: "+usuario.getTipoUsuario());
    		if (usuario.getTipoUsuario().equals("cliente")) {
    			new ConsolaClientes(parque, usuario);
    			System.out.println("\nBienvenido al menú de cliente\n");
    			ConsolaClientes.main(args);
    		}
    		else if (usuario.getTipoUsuario().equals("empleado")) {
    			Empleado empleado= (Empleado) usuario;
    			new ConsolaEmpleados(parque, empleado);
    			System.out.println("\nBienvenido al menú de empleado\n");
    			ConsolaEmpleados.main(args);
    		}
    		else if (parque.usuarioAdministrador(usuario)) {
    			Administrador administrador= (Administrador) usuario;
    			new ConsolaAdministrador(parque, administrador);
    			System.out.println("\nBienvenido al menú de administrador\n");
    			ConsolaAdministrador.main(args);
    		}
    	}
    	else {
    		System.out.println("\nIngresó su contraseña o login incorrectamente.\n");
    	}
    }
}
