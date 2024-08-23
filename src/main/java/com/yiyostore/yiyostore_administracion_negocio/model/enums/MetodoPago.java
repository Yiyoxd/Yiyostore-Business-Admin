package com.yiyostore.yiyostore_administracion_negocio.model.enums;

/**
 * Enumeración que representa los distintos métodos de pago disponibles en el
 * sistema de gestión de dispositivos. Proporciona una lista de opciones
 * estandarizadas que un usuario puede elegir para realizar sus pagos.
 */
public enum MetodoPago {
    /**
     * Pago realizado a través de tarjeta de crédito. Este método permite a los
     * usuarios pagar directamente utilizando sus tarjetas de crédito.
     */
    TARJETA_CREDITO,
    /**
     * Pago realizado a través de tarjeta de débito. Similar a la tarjeta de
     * crédito, pero los fondos se toman directamente de la cuenta bancaria del
     * usuario.
     */
    TARJETA_DEBITO,
    /**
     * Pago realizado a través de PayPal. PayPal es un servicio de pago en línea
     * que permite a los usuarios pagar, enviar dinero y aceptar pagos sin
     * revelar sus detalles financieros.
     */
    PAYPAL,
    /**
     * Pago realizado a través de transferencia bancaria directa. Este método
     * permite a los usuarios transferir fondos directamente de una cuenta
     * bancaria a otra.
     */
    TRANSFERENCIA_BANCARIA,
    /**
     * Pago realizado en efectivo al momento de recoger o entregar el
     * dispositivo. Este método se utiliza generalmente cuando las transacciones
     * se realizan físicamente en una tienda o en un punto de venta.
     */
    EFECTIVO,
    /**
     * Pago realizado mediante depósito en efectivo en una sucursal bancaria o
     * cajero automático. Este método es común para usuarios que prefieren no
     * usar métodos de pago digitales.
     */
    DEPOSITO_EFECTIVO,
    /**
     * Pago realizado a través de Mercado Pago. Mercado Pago es una plataforma
     * de pagos en línea ampliamente utilizada en América Latina que permite a
     * los usuarios realizar pagos seguros en internet.
     */
    MERCADO_PAGO
}
