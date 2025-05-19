package consola;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Atracciones.Atraccion;
import Atracciones.AtraccionMecanica;
import Atracciones.Espectaculo;
import LugarServicios.LugarServicio;
import LugarServicios.Taquilla;
import Modelo.Compra;
import Modelo.CompraAtracciones;
import Modelo.Parque;
import Tiquetes.Entrada;
import Tiquetes.FastPass;
import Tiquetes.Tiquete;
import Tiquetes.TiqueteRegular;
import Tiquetes.TiqueteTemporada;
import Usuarios.Usuario;

public class ConsolaClientes extends ConsolaUsuario{
	private static final int PRECIO_BASICO= 20;
	private static final int PRECIO_FAMILIAR= 30;
	private static final int PRECIO_ORO= 40;
	private static final int PRECIO_DIAMANTE= 50;
	private static final int PRECIO_TEMPORADA= 200;
	private static final int PRECIO_MEDIO= 10;
	private static final int PRECIO_ALTO= 20;
	private static final int PRECIO_FAST= 15;
	private static Usuario  usuario;
	
	public ConsolaClientes(Parque parque, Usuario usuario) {
		super(parque);
		ConsolaClientes.usuario=usuario;
	}
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- MENÚ CLIENTE ---");
            System.out.println("1. Comprar Tiquetes");
            System.out.println("2. Ver tiquetes disponibles");
            System.out.println("3. Ver historial de Compras");
            System.out.println("4. Entrar a parque");
            System.out.println("5. Cambiar contraseña");
            System.out.println("6. Salir al menú anterior");
            System.out.print("Opción: ");
            try {
            	int opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
            case 1 -> ComprarTiquetes(scanner);
            case 2 -> verTiquetes();
            case 3 -> verCompras();
            case 4 -> EntrarAParque(scanner);
            case 5 ->{
            	new ConsolaCambiarInfo(usuario);
            	System.out.println("\nBienvenido al menú de cambio de contraseña\n");
            	ConsolaCambiarInfo.main(args);
            }
            case 6 -> {
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
	
	private static void verCompras() {
		List<Compra> compras= usuario.getHistorialCompras();
		System.out.println("\n--- SU HISTORIAL DE COMPRAS ---");
		if (compras.size()>0) {
			int total=1;
			for(Compra i: compras) {
				if(i.getTipo().equals("atracciones")) {
					System.out.println("\n"+total+".\n");
					System.out.println("\nEsta compra fue de tiquetes y tuvo un valor de: "+i.getPrecio()+"\n");
					System.out.println("\n--- TIQUETES Y FASTPASS EN ESTA COMPRA ---");
					CompraAtracciones com=(CompraAtracciones) i;
					int tiquete=1;
					for(Tiquete a: com.getTiquetes()) {
						System.out.println(tiquete+". "+a.getTipo());
						if (!a.getTipo().equals("entrada")) {
							System.out.println("Exclusividad: "+ a.getExclusividad()+"\n");
						}
						tiquete++;
					}
					System.out.println("En total compró "+(tiquete-1)+" tiquetes y "+com.getFastPass().size()+" FastPass\n");
					total++;
				}
				else {
					System.out.println("\n"+total+".\n");
					System.out.println("\nEsta compra fue en un lugar de servicio y tuvo un valor de: "+i.getPrecio()+"\n");
					total++;
				}
			}
			System.out.println("\nEn total ha realizado: "+(total-1)+" compras\n");
		}
		else {
			System.out.println("\nNo ha realizado compras hasta el momento\n");
		}
	}

	private static void ComprarTiquetes(Scanner scanner) {
		ArrayList<Tiquete> tiquetes= new ArrayList<Tiquete>();
		ArrayList<FastPass> fastPasses= new ArrayList<FastPass>();
        
        boolean continuar = true; 
        while(continuar) {
        	System.out.println("\n--- COMPRAR TIQUETES ---");
            System.out.print("Tipo tiquete: ");
            System.out.println("\n1. Tiquete Regular");
            System.out.println("2. Tiquete Temporada");
            System.out.println("3. Entrada");
            System.out.println("4. FastPass");
            System.out.println("5. Finalizar Compra");
            System.out.print("Opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());
            
        	switch (opcion) {
            case 1 -> ComprarTiqueteRegular(scanner, tiquetes);
            case 2 -> ComprarTiqueteTemporada(scanner, tiquetes);
            case 3 -> ComprarEntrada(scanner, tiquetes);
            case 4 -> ComprarFastPass(scanner, fastPasses);
            case 5 -> {
            	FinalizarCompra(scanner, tiquetes, fastPasses);
            	continuar= false;
            }
            default -> System.out.println("Opción no válida."); 
            }
        }
	}
	
	private static Atraccion getAtraccion(Scanner scanner) {
		Atraccion atraccion=null;
		if (parque.getAtracciones().size()>0) {
			List<Atraccion> atracciones= parque.getAtracciones();
			int posicion= 0;
			while(posicion < atracciones.size()) {
				int numero= posicion +1;
				atraccion= atracciones.get(posicion);
				System.out.println("\n"+numero+": "+atraccion.getNombre());
				posicion ++;
			}
			System.out.println("\nEscoja el número de la atracción que desea: ");
			int opcion = Integer.parseInt(scanner.nextLine());
			if (opcion>=1 && opcion <= atracciones.size()) {
				atraccion = atracciones.get(opcion-1);
				return atraccion;
				}
			else {
				System.out.println("Opción no válida.\n");
				return null;
			}
		}
		else {
			System.out.println("\nNo hay atracciones en este momento\n");
			return null;
		}
	}
	
	private static void ComprarTiqueteRegular(Scanner scanner, ArrayList<Tiquete> tiquetes) {
		System.out.println("\n--- ESCOGER EXCLUSIVIDAD ---");
		System.out.println("1. Básico");
        System.out.println("2. Familiar");
        System.out.println("3. Oro");
        System.out.println("4. Diamante");
        System.out.print("Opción: ");
        int opcion = Integer.parseInt(scanner.nextLine());
        
        switch (opcion) {
        case 1 -> {
        	TiqueteRegular tiquete= new TiqueteRegular("basico", usuario, PRECIO_BASICO);
        	tiquetes.addLast(tiquete);
        	System.out.print("\nSe agregó exitosamente el tiquete regular\n");
        }
        case 2 -> {
        	TiqueteRegular tiquete= new TiqueteRegular("familiar", usuario, PRECIO_FAMILIAR);
        	tiquetes.addLast(tiquete);
        	System.out.print("\nSe agregó exitosamente el tiquete regular\n");
        }
        case 3 -> {
        	TiqueteRegular tiquete= new TiqueteRegular("oro", usuario, PRECIO_ORO);
        	tiquetes.addLast(tiquete);
        	System.out.print("\nSe agregó exitosamente el tiquete regular\n");
        }
        case 4 ->{
        	TiqueteRegular tiquete= new TiqueteRegular("diamante", usuario, PRECIO_DIAMANTE);
        	tiquetes.addLast(tiquete);
        	System.out.print("\nSe agregó exitosamente el tiquete regular\n");
        }
        default -> System.out.println("\nOpción no válida.\n");
        }
	}
    
	private static void ComprarTiqueteTemporada(Scanner scanner, ArrayList<Tiquete> tiquetes) {
		String exclusividad=null;
		String inicio=null;
    	String fin=null;
    	int precio=0;
		System.out.println("\n--- ESCOGER EXCLUSIVIDAD ---");
		System.out.println("1. Básico");
        System.out.println("2. Familiar");
        System.out.println("3. Oro");
        System.out.println("4. Diamante");
        System.out.print("Opción: ");
        int opcion = Integer.parseInt(scanner.nextLine());
        
        switch (opcion) {
        case 1 -> {
        	exclusividad="basico";
        	precio= PRECIO_TEMPORADA + PRECIO_BASICO;
        }
        case 2 -> {
        	exclusividad="familiar";
        	precio= PRECIO_TEMPORADA + PRECIO_FAMILIAR;
        }
        case 3 -> {
        	exclusividad="oro";
        	precio= PRECIO_TEMPORADA + PRECIO_ORO;
        }
        case 4 ->{
        	exclusividad="diamante";
        	precio= PRECIO_TEMPORADA + PRECIO_DIAMANTE;
        }
        default -> System.out.println("Opción no válida.");
        }
        
        System.out.println("\n--- ESCOGER TEMPORADA ---");
		System.out.println("1. PRIMAVERA");
        System.out.println("2. VERANO");
        System.out.println("3. OTOÑO");
        System.out.println("4. INVIERNO");
        System.out.print("Opción: ");
        opcion = Integer.parseInt(scanner.nextLine());
        switch (opcion) {
        case 1 -> {
        	inicio= "01-03";
        	fin= "31-05";
        }
        case 2 -> {
        	inicio= "01-06";
        	fin= "30-08";
        }
        case 3 -> {
        	inicio= "01-09";
        	fin= "30-11";
        }
        case 4 ->{
        	inicio= "01-12";
        	fin= "28-02";
        }
        default -> System.out.println("\nOpción no válida.\n");
        }
        TiqueteTemporada tiquete= new TiqueteTemporada(exclusividad, usuario, precio, inicio, fin);
        tiquetes.addLast(tiquete);
        System.out.print("\nSe agregó exitosamente el tiquete de temporada\n");
	}
	
	private static void ComprarEntrada(Scanner scanner, ArrayList<Tiquete> tiquetes) {
		Atraccion atraccion;
		int precio=0;
		try {
			atraccion = getAtraccion(scanner);
			if (atraccion!= null) {
				if (atraccion.getTipo().compareTo("mecanica")==0) {
					AtraccionMecanica atract= (AtraccionMecanica) atraccion;
					if (atract.getNivel().compareTo("alto")==0) {
						precio= PRECIO_ALTO;
						Entrada entrada= new Entrada(usuario, precio, atract);
						tiquetes.addLast(entrada);
						System.out.print("Se agregó exitosamente la entrada\n");
					}
					else {
						precio= PRECIO_MEDIO;
						Entrada entrada= new Entrada(usuario, precio, atract);
						tiquetes.addLast(entrada);
						System.out.print("Se agregó exitosamente la entrada\n");
					}
				}
				else {
					precio= PRECIO_MEDIO;
					Entrada entrada= new Entrada(usuario, precio, atraccion);
					tiquetes.addLast(entrada);
					System.out.print("Se agregó exitosamente la entrada");
				}
			}
		}
			catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void ComprarFastPass(Scanner scanner, ArrayList<FastPass> fastPasses) {
		Date fecha= new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		String dia = formato.format(fecha);
		FastPass fast= new FastPass(dia, usuario, PRECIO_FAST);
		fastPasses.addLast(fast);
		System.out.print("\nSe agregó exitosamente el fastPass\n");
	}
	
	private static void FinalizarCompra(Scanner scanner, ArrayList<Tiquete> tiquetes, ArrayList<FastPass> fastPasses) {
		if (tiquetes.isEmpty() && fastPasses.isEmpty()) {
			System.out.print("No hay tiquetes o FastPass agregados, entonces se cancela la compra\n");
		}
		else {
			float total= parque.calcularPrecioCompraTiquetes(usuario, tiquetes, fastPasses);
			System.out.print("\nEl total de su compra son "+ tiquetes.size()+" tiquetes y "+ fastPasses.size()+" FastPass.\n");
			if (usuario.getTipoUsuario().compareTo("cliente") ==0) {
				System.out.print("\nEl precio de su compra es: "+total);
			}
			else {
				System.out.print("\nEl precio de su compra con descuento por ser trabajador es: "+total+"\n");
			}
			System.out.print("\nDesea aceptar la compra? (si/no): ");
			String opcion= scanner.nextLine();
			if (opcion.compareTo("si")==0) {
				parque.registrarCompraTiquetesOnline(usuario, tiquetes, fastPasses);
				System.out.print("\nSu compra fue realizada exitosamente, disfrutela!\n");
			}
			else if (opcion.compareTo("no")==0) {
				System.out.print("\nEntendemos, su compra fue cancelada\n");
			}
			else {
				System.out.print("\nNo escogió bien la opción entre si y no\n");
			}
		}
	}
	
	private static void verTiquetes() {
		System.out.println("\n--- SUS TIQUETES Y FASTPASS DISPONIBLES ---");
		System.out.println("Tiquetes: \n");
		int tiquete=1;
		for(Tiquete i: usuario.getTiquetesPorUsar()) {
			System.out.println(tiquete+". "+i.getTipo());
			if (!i.getTipo().equals("entrada")){
				System.out.println("Exclusividad: "+ i.getExclusividad()+"\n");
			}
			tiquete++;
		}
		System.out.println("Cuenta con "+usuario.getFastPassPorUsar().size()+" FastPass por usar");
		tiquete-=1;
		System.out.println("En total cuenta con "+tiquete+" tiquetes y "+usuario.getFastPassPorUsar().size()+" FastPass\n");
	}
	
	private static void EntrarAParque(Scanner scanner) {
		if (usuario.getTiquetesPorUsar().size()>0) {
			verTiquetes();
			List<Tiquete> tiquetes= usuario.getTiquetesPorUsar();
			System.out.println("Escoja el tiquete que desea para ingresar al parque (número tiquete)");
			System.out.print("Opción: ");
			try {
				int opcion = Integer.parseInt(scanner.nextLine().strip());
		        if (opcion>=1 && opcion<= usuario.getTiquetesPorUsar().size()) {
		        	Tiquete tiquete= tiquetes.get(opcion-1);
		        	Date fecha= new Date();
		        	SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		        	String texto = formato.format(fecha);
		        	boolean valido= parque.registrarEntrada(tiquete, texto, usuario.getFastPassPorUsar());
		        	
		        	if (valido) {
		        		System.out.println("\nSu ingreso al parque fue exitoso! \n");
		                boolean continuar =true;
		                while (continuar) {
			        		System.out.println("\n--- ACTIVIDADES QUE HACER ---");
			                System.out.println("1. Ingresar a Atracción");
			                System.out.println("2. Comprar entrada para atracción en taquilla");
			                System.out.println("3. Ir a espectaculo");
			                System.out.println("4. Salir del parque");
			                System.out.print("Opción: ");
			                opcion = Integer.parseInt(scanner.nextLine());
		                	switch (opcion) {
		                    case 1 -> ingresarAtraccion(scanner);
		                    case 2 -> comprarEntradaTaquilla(scanner);
		                    case 3 -> entrarEspectaculo(scanner);
		                    case 4 -> {
		                    	System.out.println("\nLo esperamos pronto! \n");
		                    	parque.registrarSalida(tiquete, texto, usuario.getFastPassPorUsar());
		                        continuar = false;
		                        }
		                    default -> System.out.println("Opción no válida");
		                     }
		                	
		                }
		        	}
		        	else {
		        		System.out.println("\nLo sentimos pero su ingreso al parque no fue exitoso\n");
		        	}
		        }
		        else {
		        	System.out.println("\nOpción de tiquete no válido\n");
		        }
			} catch(Exception e) {
				System.out.println("Escoja un número por favor");
			}
		}
		
		else {
			System.out.print("\nEn este momento no tiene tiquetes para ingresar al parque\n");
		}
	}
	
	private static void comprarEntradaTaquilla(Scanner scanner) {
		Atraccion atraccion;
		int precio=0;
		List<Tiquete> tiquetes= new ArrayList<Tiquete>();
		List<FastPass> fast= new ArrayList<FastPass>();
		Taquilla taquilla=null;
		for (LugarServicio i: parque.getLugares()) {
			if (i.getTipo().compareTo("taquilla")==0) {
				taquilla= (Taquilla)i;
			}
		}
		if (taquilla!= null) {
			try {
				atraccion = getAtraccion(scanner);
				if (atraccion!= null) {
					if (atraccion.getTipo().compareTo("mecanica")==0) {
						AtraccionMecanica atract= (AtraccionMecanica) atraccion;
						if (atract.getNivel().compareTo("alto")==0) {
							precio= PRECIO_ALTO;
							Entrada entrada= new Entrada(usuario, precio, atract);
							tiquetes.addLast(entrada);
						}
						else {
							precio= PRECIO_MEDIO;
							Entrada entrada= new Entrada(usuario, precio, atract);
							tiquetes.addLast(entrada);
						}
					}
					else {
						precio= PRECIO_MEDIO;
						Entrada entrada= new Entrada(usuario, precio, atraccion);
						tiquetes.addLast(entrada);
					}
					try {
						parque.registrarCompraTiquetesTaquilla(usuario, tiquetes, fast, taquilla);
						System.out.print("\nSu entrada fue adquirida exitosamente\n");
						
					}
					catch(Exception e) {
						System.out.print("\nLa taquilla no cuenta con un cajero para atender la compra, lo sentimos\n");
					}
				}
			}
				catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.print("\nNo hay una taquilla disponible para comprar tiquetes\n");
		}
	}
	
	private static void ingresarAtraccion(Scanner scanner) {
		Atraccion atraccion= getAtraccion(scanner);
		if (atraccion!= null) {
			verTiquetes();
			List<Tiquete> tiquetes= usuario.getTiquetesPorUsar();
			System.out.println("\nEscoja el tiquete que desea para ingresar a la atracción (número tiquete)");
			System.out.print("Opción: ");
	        int opcion = Integer.parseInt(scanner.nextLine());
	        if (opcion>=1 && opcion<= usuario.getTiquetesPorUsar().size()) {
	        	Tiquete tiquete= tiquetes.get(opcion-1);
	        	try {
	        		parque.IngresarAtraccion(atraccion, tiquete);
	        		if (usuario.getFastPassPorUsar().size()>0) {
	        			System.out.print("\nEl cliente pudo ingresar con FastPass a la atracción\n");
	        		}
	        	}
	        	catch (Exception e) {
	        		System.out.print("\n"+e.getMessage()+"\n");
	        	}
	        }
	        else {
	        	System.out.print("\nOpción no válida\n");
	        }
		}
	}
	
	private static void entrarEspectaculo(Scanner scanner) {
		if (parque.getEspectaculos().size()>0) {
			List<Espectaculo> espectaculos= parque.getEspectaculos();
			int posicion= 0;
			Espectaculo espectaculo=null;
			while(posicion < espectaculos.size()) { 
				int numero= posicion +1;
				espectaculo= espectaculos.get(posicion);
				System.out.println(numero+": "+espectaculo.getNombre());
				posicion ++;
			}
			System.out.println("\nEscoja el número del espectaculo que desea asistir: ");
			System.out.print("Opción: ");
			int opcion = Integer.parseInt(scanner.nextLine());
			if (opcion>=1 && opcion <= espectaculos.size()) {
				espectaculo = espectaculos.get(opcion-1);
				if (espectaculo.getAbierto() && espectaculo.verificarFecha()) {
					System.out.println("\nIngresó correctamente al espectáculo\n");
				}
				else {
					System.out.println("\nLo sentimos, el espectáculo esta cerrado\n");
				}
			
			}
			else {
				System.out.println("\nOpción no válida.\n");
			}
		}
		else {
			System.out.println("\nEn este momento no hay espectaculos en el parque.\n");
		}
	}
}
