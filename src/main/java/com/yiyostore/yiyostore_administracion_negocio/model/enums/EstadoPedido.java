package com.yiyostore.yiyostore_administracion_negocio.model;

/**
 * Enum que representa los posibles estados de un pedido en el sistema.
 *
 * Este enum se utiliza para rastrear y gestionar el estado de un pedido desde
 * su creación hasta su finalización. Los estados ayudan a entender en qué etapa
 * del proceso se encuentra el pedido y permiten aplicar la lógica de negocio
 * correspondiente en cada caso.
 */
public enum EstadoPedido {

    /**
     * PENDIENTE: El pedido ha sido creado pero aún no se ha procesado.
     *
     * Este es el estado inicial de un pedido recién creado. El pedido está
     * esperando para ser enviado.
     */
    PENDIENTE,
    /**
     * EN_PROCESO: El pedido está siendo procesado.
     *
     * En este estado, el pedido está en proceso de entrega.
     */
    EN_PROCESO,

    /**
     * COMPLETADO: El pedido ha sido entregado y completado.
     *
     * El pedido ha llegado al cliente y se ha confirmado la entrega. Este es el
     * estado final de un pedido exitoso.
     */
    COMPLETADO,
    /**
     * CANCELADO: El pedido ha sido cancelado.
     *
     * El pedido ha sido cancelado por el cliente o por el sistema antes de que
     * pudiera ser completado. Este estado finaliza el pedido sin que llegue a
     * completarse.
     */
    CANCELADO,
    /**
     * DEVUELTO: El pedido ha sido devuelto por el cliente.
     *
     * El cliente ha devuelto el pedido después de que fue entregado. Este
     * estado indica que el pedido ha sido finalizado pero con una devolución.
     */
    DEVUELTO
}