package Tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Usuarios.Administrador;
import Usuarios.Cocinero;
import Usuarios.EmpleadoServiciosgenerales;
import LugarServicios.Cafeteria;

public class AdministradorTest {

    private Administrador admin;

    @BeforeEach
    public void setUp() {
        admin = new Administrador("admin", "1234", 35, 1.80f);
    }

    @Test
    public void testAsignarTurno_EmpleadoValido() {
    	Cocinero cocinero = new Cocinero("coci", "clave", 30, 1.75f, "chef");

        assertDoesNotThrow(() -> admin.AsignarTurno(cocinero, "mañana"));
        assertEquals("mañana", cocinero.getTurnos().get(0));

    }

    @Test
    public void testAsignarTurno_EmpleadoServiciosGeneralesLanzaExcepcion() {
    	EmpleadoServiciosgenerales emp = new EmpleadoServiciosgenerales(
    		    "juan", "pass", 25, 1.70f, null, "limpieza"
    		);

        Exception ex = assertThrows(Exception.class, () -> admin.AsignarTurno(emp, "tarde"));
        assertEquals("Los empleados de servicios generales no tienen turno", ex.getMessage());
    }

    @Test
    public void testAsignarLabor_CocineroCajaEnCafeteria() throws Exception {
        Cocinero cocinero = new Cocinero("coci", "clave", 30, 1.75f, "chef");

        Cafeteria cafeteria = new Cafeteria("Café Central");
        admin.AsignarLabor(cocinero, "caja", cafeteria);
        assertEquals("caja", cocinero.getLabor());
        assertEquals(cocinero, cafeteria.getCajero());
    }

}