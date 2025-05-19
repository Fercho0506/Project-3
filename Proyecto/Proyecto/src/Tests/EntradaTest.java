package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Tiquetes.Entrada;
import Atracciones.AtraccionMecanica;
import Usuarios.Cliente;

import java.util.Date;

public class EntradaTest {

	@Test
	public void testCrearEntradaConAtraccion() {
	    // Crear cliente
	    Cliente cliente = new Cliente("pepito", "1234", 20, 1.70f);

	    // Crear atracción
	    AtraccionMecanica atraccion = new AtraccionMecanica(
	        "Rueda Panorámica", 15, "Centro", 1, "ninguna", new Date(), 1.80f, 1.30f, "bajo"
	    );

	    // Crear entrada
	    Entrada entrada = new Entrada(cliente, 5000, atraccion);

	    // Verificar datos
	    assertEquals(atraccion, entrada.getAtraccion());
	    assertEquals("entrada", entrada.getTipo());
	    assertEquals(5000, entrada.getPrecio());
	    assertEquals(cliente, entrada.getUsuario());
	}

}