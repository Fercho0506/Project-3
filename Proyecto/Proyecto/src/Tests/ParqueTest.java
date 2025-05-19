package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modelo.Parque;
import Usuarios.Cliente;
import Usuarios.Administrador;
import Usuarios.Usuario;

public class ParqueTest {

    private Parque parque;

    @BeforeEach
    public void setUp() {
        parque = new Parque("Calle 123", "Divertilandia", 5000);
    }

    @Test
    public void testParqueInicialmenteCerrado() {
        assertFalse(parque.getAbierto());
    }

    @Test
    public void testAbrirYCerrarParque() {
        parque.abrirParque();
        assertTrue(parque.getAbierto());
        parque.cerrarParque();
        assertFalse(parque.getAbierto());
    }

    @Test
    public void testGettersYSettersBasicos() {
        parque.setNombre("NuevoNombre");
        parque.setCapacidad(8000);
        parque.setDireccion("Carrera 45");
        assertEquals("NuevoNombre", parque.getNombre());
        assertEquals(8000, parque.getCapacidad());
        assertEquals("Carrera 45", parque.getDireccion());
    }

    @Test
    public void testGenerarCodigoCompra() {
        int codigo = parque.generarCodigoCompra();
        assertTrue(codigo > 0 && codigo < 100000000);
    }

    @Test
    public void testAgregarUsuarioExitoso() throws Exception {
        Cliente cliente = new Cliente("pepito", "1234", 20, 1.70f);
        parque.agregarUsuario(cliente);
        assertEquals(cliente, parque.getUsuario("pepito"));
    }

    @Test
    public void testAgregarUsuarioRepetidoLanzaExcepcion() throws Exception {
        Cliente cliente1 = new Cliente("pepito", "1234", 20, 1.70f);
        Cliente cliente2 = new Cliente("pepito", "abcd", 25, 1.80f);
        parque.agregarUsuario(cliente1);
        Exception ex = assertThrows(Exception.class, () -> parque.agregarUsuario(cliente2));
        assertTrue(ex.getMessage().contains("Ya hay un usuario con ese login"));
    }

    @Test
    public void testAutenticacionExitosa() throws Exception {
        Cliente cliente = new Cliente("juanita", "clave123", 19, 1.60f);
        parque.agregarUsuario(cliente);
        assertTrue(parque.autenticarIngreso("juanita", "clave123"));
    }

    @Test
    public void testAutenticacionFallida() throws Exception {
        Cliente cliente = new Cliente("juanita", "clave123", 19, 1.60f);
        parque.agregarUsuario(cliente);
        assertFalse(parque.autenticarIngreso("juanita", "claveIncorrecta"));
        assertFalse(parque.autenticarIngreso("noExiste", "clave123"));
    }

    @Test
    public void testUsuarioAdministrador() throws Exception {
        Administrador admin = new Administrador("admin", "adminpass", 40, 1.80f);
        parque.agregarUsuario(admin);
        Usuario user = parque.getUsuario("admin");
        assertTrue(parque.usuarioAdministrador(user));
    }

    @Test 
    public void testEliminarUsuario() throws Exception {
        Cliente cliente = new Cliente("luis", "qwerty", 30, 1.75f);
        parque.agregarUsuario(cliente);
        assertTrue(parque.eliminarUsuario(cliente));
        assertNull(parque.getUsuario("luis"));
    }

}