package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import LugarServicios.Taquilla;

public class TaquillaTest {

    @Test
    public void testCrearTaquilla() {
        Taquilla taquilla = new Taquilla("Entrada Principal");

        assertEquals("taquilla", taquilla.getTipo());
        assertEquals("Entrada Principal", taquilla.getNombre());
    }

    @Test
    public void testModificarNombre() {
        Taquilla taquilla = new Taquilla("Zona Norte");
        taquilla.setNombre("Zona Sur");

        assertEquals("Zona Sur", taquilla.getNombre());
    }

}
