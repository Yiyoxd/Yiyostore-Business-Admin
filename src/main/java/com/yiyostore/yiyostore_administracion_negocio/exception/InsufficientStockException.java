package com.yiyostore.yiyostore_administracion_negocio.exception;

/**
 * Excepción que se lanza cuando no hay suficiente stock disponible para un
 * producto.
 */
public class InsufficientStockException extends RuntimeException {

    /**
     * Constructor con un mensaje específico.
     *
     * @param mensaje El mensaje de error.
     */
    public InsufficientStockException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor con un mensaje y una causa específicos.
     *
     * @param mensaje El mensaje de error.
     * @param causa La causa de la excepción.
     */
    public InsufficientStockException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
