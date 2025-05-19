package consola;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import java.util.Scanner;

import Atracciones.AtraccionMecanica;
import Atracciones.Espectaculo;
import LugarServicios.Cafeteria;
import LugarServicios.LugarServicio;
import Modelo.Parque;
import Usuarios.Administrador;
import Usuarios.Cajero;
import Usuarios.Cocinero;
import Usuarios.Empleado;
import Usuarios.EmpleadoAtracciones;
import Usuarios.EmpleadoServiciosgenerales;

public class ConsolaAdministrador extends ConsolaUsuario {
    private static Administrador administrador;

    public ConsolaAdministrador(Parque parque, Administrador administrador) {
        super(parque);
        ConsolaAdministrador.administrador = administrador;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- MENÚ ADMINISTRADOR ---");
            System.out.println("1. Añadir atracción");
            System.out.println("2. Añadir espectáculo");
            System.out.println("3. Añadir empleado");
            System.out.println("4. Añadir lugar de servicio");
            System.out.println("5. Ir al menú de cliente");
            System.out.println("6. Cambiar contraseña");
            System.out.println("7. Salir al menú principal");
            System.out.print("Opción: ");
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1 -> agregarAtraccion(scanner);
                    case 2 -> agregarEspectaculo(scanner);
                    case 3 -> agregarEmpleado(scanner);
                    case 4 -> agregarLugarServicio(scanner);
                    case 5 -> {
                        new ConsolaClientes(parque, administrador);
                        System.out.println("\nBienvenido al menú de cliente\n");
                        ConsolaClientes.main(args);
                    }
                    case 6 -> {
                        new ConsolaCambiarInfo(administrador);
                        System.out.println("\nBienvenido al menú de cambio de contraseña\n");
                        ConsolaCambiarInfo.main(args);
                    }
                    case 7 -> {
                        continuar = false;
                    }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ Escoja un número válido por favor.");
            }
        }
        System.out.println("\n¡Lo esperamos pronto!\n");
    }

    private static void agregarAtraccion(Scanner scanner) {
        try {
            System.out.println("\n--- AÑADIR ATRACCIÓN ---");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Edad mínima: ");
            System.out.print("Estatura mínima (cm): ");
            int estaturaMin = Integer.parseInt(scanner.nextLine());
            System.out.print("¿Es mecánica? (s/n): ");
            String mecanica = scanner.nextLine();

            if (mecanica.equalsIgnoreCase("s")) {
                Date fechaTemporada = new Date(); // Por ahora la fecha actual
                AtraccionMecanica atraccion = new AtraccionMecanica(
                    nombre,
                    30,
                    "Zona A",
                    2,
                    "Ninguna",
                    fechaTemporada,
                    2.0f,
                    estaturaMin / 100.0f,
                    "alto"
                );

                parque.agregaratraccion(atraccion);
                System.out.println("✔️ Atracción mecánica agregada.");
            } else {
                System.out.println("❌ Por ahora solo se implementa la creación de atracciones mecánicas.");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error al agregar la atracción: " + e.getMessage());
        }
    }

    private static void agregarEspectaculo(Scanner scanner) {
        System.out.println("\n--- AÑADIR ESPECTÁCULO ---");
        try {
            System.out.print("Nombre del espectáculo: ");
            String nombre = scanner.nextLine();
            System.out.print("Fecha del espectáculo (dd-MM-yyyy): ");
            String fechaStr = scanner.nextLine();
            System.out.print("Horario: ");
            String horario = scanner.nextLine();

            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            Date fecha = formato.parse(fechaStr);

            Espectaculo espectaculo = new Espectaculo(nombre, fecha, horario);
            parque.agregarEspectaculo(espectaculo);
            System.out.println("✔️ Espectáculo agregado.");
        } catch (ParseException e) {
            System.out.println("⚠️ Formato de fecha inválido. Usa dd-MM-yyyy.");
        }
    }

    private static void agregarEmpleado(Scanner scanner) {
        System.out.println("\n--- AÑADIR EMPLEADO ---");
        try {
            System.out.print("Nombre de usuario: ");
            String usuario = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();
            System.out.print("Edad: ");
            int edad = Integer.parseInt(scanner.nextLine());
            System.out.print("Altura (ej: 1.75): ");
            float altura = Float.parseFloat(scanner.nextLine());

            System.out.println("Tipo de empleado:");
            System.out.println("1. Cajero");
            System.out.println("2. Cocinero");
            System.out.println("3. Empleado de atracciones");
            System.out.println("4. Servicios generales");
            System.out.print("Opción: ");
            int tipo = Integer.parseInt(scanner.nextLine());

            System.out.println("Tipo de labor:");
            System.out.println("1. Caja");
            System.out.println("2. Cocina");
            System.out.println("3. Manejo Atracción");
            System.out.println("4. Limpieza");
            System.out.println("5. Sin labor por ahora");
            System.out.print("Opción: ");
            int labo = Integer.parseInt(scanner.nextLine());

            String labor = "";
            switch (labo) {
               case 1 -> labor="caja";
               case 2 -> labor="cocina";
               case 3 -> labor="atraccion";
               case 4 -> labor="limpieza";
               case 5-> labor="";
               default -> System.out.print("\nPor favor escoja una opción correcta.\n");
               };
      

            Empleado empleado = null;

            switch (tipo) {
                case 1 -> empleado = new Cajero(usuario, password, edad, altura, "");
                case 2 -> empleado = new Cocinero(usuario, password, edad, altura, "");
                case 3 -> {
                    System.out.print("Nivel de riesgo autorizado (medio, alto): ");
                    String nivel = scanner.nextLine();
                    empleado = new EmpleadoAtracciones(usuario, password, edad, altura, "", nivel);
                }
                case 4 -> {
                    List<LugarServicio> lugares = parque.getLugares();
                    if (lugares.isEmpty()) {
                        System.out.println("⚠️ No hay lugares de servicio disponibles.");
                        return;
                    }
                    System.out.println("Seleccione el lugar a asignar:");
                    for (int i = 0; i < lugares.size(); i++) {
                        System.out.println((i + 1) + ". " + lugares.get(i).getNombre());
                    }
                    int index = Integer.parseInt(scanner.nextLine()) - 1;
                    Cafeteria cafeteria = (Cafeteria) lugares.get(index);
                    empleado = new EmpleadoServiciosgenerales(usuario, password, edad, altura, cafeteria, labor);
                }
            }

            if (empleado != null) {
            	try {
            		parque.agregarUsuario(empleado);
                    administrador.AsignarLabor(empleado, labor, null);
                    System.out.println("✔️ Empleado agregado.");
            	} catch (Exception e) {
            		System.out.println("No se pudo agregar el empleado por: " + e.getMessage());
            	}
            }

        } catch (Exception e) {
            System.out.println("⚠️ Error al agregar empleado: " + e.getMessage());
        }
    }

    private static void agregarLugarServicio(Scanner scanner) {
        System.out.println("\n--- AÑADIR LUGAR DE SERVICIO ---");
        System.out.print("Nombre del lugar: ");
        String nombre = scanner.nextLine();

        Cafeteria cafeteria = new Cafeteria(nombre);
        parque.agregarLugar(cafeteria);
        System.out.println("✔️ Cafetería agregada.");
    }
}
