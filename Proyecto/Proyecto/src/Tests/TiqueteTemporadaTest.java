package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Tiquetes.TiqueteTemporada;
import Usuarios.Cliente;

public class TiqueteTemporadaTest {

    @Test
    public void testCrearTiqueteTemporada() {
        Cliente cliente = new Cliente("juan", "clave123", 25, 1.75f);
        TiqueteTemporada tiquete = new TiqueteTemporada("vip", cliente, 10000, "01-01", "31-12");

        assertEquals("vip", tiquete.getExclusividad());
        assertEquals(cliente, tiquete.getUsuario());
        assertEquals(10000, tiquete.getPrecio());
        assertEquals("temporada", tiquete.getTipo());
        assertEquals("01-01", tiquete.getInicio());
        assertEquals("31-12", tiquete.getFin());
    }

    @Test
    public void testGettersDeFechas() {
        Cliente cliente = new Cliente("maria", "pass456", 22, 1.68f);
        TiqueteTemporada tiquete = new TiqueteTemporada("gold", cliente, 12000, "15-06", "15-07");

        assertEquals("15-06", tiquete.getInicio());
        assertEquals("15-07", tiquete.getFin());
    }

}