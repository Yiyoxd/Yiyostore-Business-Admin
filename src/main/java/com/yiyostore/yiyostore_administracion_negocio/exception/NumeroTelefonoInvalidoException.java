package com.yiyostore.yiyostore_administracion_negocio.exception;

/**
 * Excepción personalizada que se lanza cuando un número de teléfono no es
 * válido o no se puede normalizar.
 * <p>
 * Esta excepción extiende {@link RuntimeException}, por lo que no es necesario
 * capturarla explícitamente en tiempo de compilación. Se utiliza para indicar
 * problemas específicos relacionados con la validación o normalización de
 * números de teléfono.
 * </p>
 */
public class NumeroTelefonoInvalidoException extends RuntimeException {

    /**
     * Constructor que crea una nueva instancia de
     * {@code NumeroTelefonoInvalidoException} con un mensaje de error.
     *
     * @param message el mensaje de error detallado que explica la causa de la
     * excepción.
     */
    public NumeroTelefonoInvalidoException(String message) {
        super(message);
    }

    /**
     * Constructor que crea una nueva instancia de
     * {@code NumeroTelefonoInvalidoException} con un mensaje de error y una
     * causa.
     *
     * @param message el mensaje de error detallado que explica la causa de la
     * excepción.
     * @param cause la causa original de la excepción (puede ser nulo).
     */
    public NumeroTelefonoInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }
}
