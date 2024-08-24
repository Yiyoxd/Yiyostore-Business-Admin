package com.yiyostore.yiyostore_administracion_negocio;

import com.yiyostore.yiyostore_administracion_negocio.exception.NumeroTelefonoInvalidoException;
import com.yiyostore.yiyostore_administracion_negocio.utils.TelefonoUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TelefonoUtilsTest {

    @Test
    public void testNormalizarNumeroValido() {
        String numeroTelefono = "87-14-14-05-50";
        String numeroEsperado = "8714140550";
        String numeroNormalizado = TelefonoUtils.normalizar(numeroTelefono);
        assertEquals(numeroEsperado, numeroNormalizado);
    }

    @Test
    public void testNormalizarNumeroValidoConEspacios() {
        String numeroTelefono = "871 414 0550";
        String numeroEsperado = "8714140550";
        String numeroNormalizado = TelefonoUtils.normalizar(numeroTelefono);
        assertEquals(numeroEsperado, numeroNormalizado);
    }

    @Test
    public void testNormalizarNumeroValidoConEspaciosXD() {
        String numeroTelefono = "+52 1 871 786 1794";
        String numeroEsperado = "8717861794";
        String numeroNormalizado = TelefonoUtils.normalizar(numeroTelefono);
        assertEquals(numeroEsperado, numeroNormalizado);
    }

    @Test
    public void testNormalizarNumeroValidoConCodigoPais() {
        String numeroTelefono = "+52 871 414 0550";
        String numeroEsperado = "8714140550";
        String numeroNormalizado = TelefonoUtils.normalizar(numeroTelefono);
        assertEquals(numeroEsperado, numeroNormalizado);
    }

    @Test
    public void testNormalizarNumeroInvalido() {
        String numeroTelefonoInvalido = "1234";

        NumeroTelefonoInvalidoException exception = assertThrows(NumeroTelefonoInvalidoException.class, () -> {
            TelefonoUtils.normalizar(numeroTelefonoInvalido);
        });

        // Verificar que el mensaje de la excepción contenga el número de teléfono inválido
        assertTrue(exception.getMessage().contains(numeroTelefonoInvalido));
    }
}
