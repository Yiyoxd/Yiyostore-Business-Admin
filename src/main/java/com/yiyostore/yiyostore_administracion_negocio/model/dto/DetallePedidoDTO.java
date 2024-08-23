package com.yiyostore.yiyostore_administracion_negocio.model.dto;

/**
 * DTO que representa un detalle dentro de un pedido. Este objeto se utiliza
 * para transferir datos entre el front-end y el back-end cuando se especifican
 * los productos y las cantidades en un pedido.
 *
 * @param idProducto ID del producto que el cliente desea comprar.
 * @param cantidad Cantidad del producto que el cliente desea comprar.
 */
public record DetallePedidoDTO(Long idProducto, int cantidad) {}