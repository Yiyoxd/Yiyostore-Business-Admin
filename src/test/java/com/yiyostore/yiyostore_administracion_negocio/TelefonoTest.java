package com.yiyostore.yiyostore_administracion_negocio;

import com.yiyostore.yiyostore_administracion_negocio.model.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TelefonoTest {

    @Test
    public void testSetNumeroTelefonoValido() {
        Cliente cliente = new Cliente();
        String telefonoValido = "8715080950";
        cliente.setNumeroTelefono(telefonoValido);
        assertEquals(telefonoValido, cliente.getNumeroTelefono());
    }

    @Test
    public void testSetNumeroTelefonoInvalido() {
        Cliente cliente = new Cliente();
        String telefonoInvalido = "12345";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cliente.setNumeroTelefono(telefonoInvalido);
        });

        String expectedMessage = "Número de teléfono inválido";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}