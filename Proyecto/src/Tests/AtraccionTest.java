package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import Atracciones.AtraccionMecanica;

public class AtraccionTest {

    @Test
    public void testCrearAtraccionMecanica() {
        Date fecha = new Date();
        AtraccionMecanica atraccion = new AtraccionMecanica(
        	    "Montaña Rusa", 20, "Zona A", 2, "ninguna", fecha, 2.00f, 1.50f, "alto"
        	);

        assertEquals("Montaña Rusa", atraccion.getNombre());
        assertEquals(20, atraccion.getCapacidad());
        assertEquals("Zona A", atraccion.getUbicacion());
        assertEquals(2, atraccion.getEmpleadosMin());
        assertEquals("ninguna", atraccion.getExclusividad());
        assertEquals("mecanica", atraccion.getTipo());
        assertFalse(atraccion.getAbierta());
        assertEquals(0, atraccion.getNumeroEmpleados());
    }

    @Test
    public void testAbrirYCerrarAtraccion() {
        Date fecha = new Date();
        AtraccionMecanica atraccion = new AtraccionMecanica(
        	    "Rueda Gigante", 30, "Zona B", 3, "VIP", fecha, 2.0f, 1.60f, "bajo"
        	);


        assertFalse(atraccion.getAbierta());

        atraccion.abrirAtraccion();
        assertTrue(atraccion.getAbierta());

        atraccion.cerrarAtraccion();
        assertFalse(atraccion.getAbierta());
    }

    @Test
    public void testSetters() {
        Date fecha = new Date();
        AtraccionMecanica atraccion = new AtraccionMecanica(
            "Rueda Panorámica", 15, "Centro", 1, "ninguna", fecha, 1.80f, 1.30f, "bajo"
        );

        atraccion.setNombre("Nuevo Carrusel");
        atraccion.setCapacidad(25);

        assertEquals("Nuevo Carrusel", atraccion.getNombre());
        assertEquals(25, atraccion.getCapacidad());
    }

}
