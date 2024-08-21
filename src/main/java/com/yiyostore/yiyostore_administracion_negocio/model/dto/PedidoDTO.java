package com.yiyostore.yiyostore_administracion_negocio.model.dto;

import com.yiyostore.yiyostore_administracion_negocio.model.enums.LugarCompra;
import com.yiyostore.yiyostore_administracion_negocio.model.enums.MetodoPago;
import java.util.List;

/**
 * DTO (Data Transfer Object) que representa un pedido realizado por un cliente.
 * Este objeto se utiliza para transferir datos entre el front-end y el back-end
 * cuando se crea o se procesa un pedido.
 *
 * @param clienteId ID del cliente que realiza el pedido.
 * @param metodoPago Método de pago seleccionado por el cliente. Debe coincidir
 * con los valores definidos en el enum {@link MetodoPago}.
 * @param lugarCompra Lugar donde se realizó la compra, según el enum
 * {@link LugarCompra}.
 * @param notas Notas adicionales que el cliente ha proporcionado sobre el
 * pedido.
 * @param detalles Lista de detalles del pedido. Cada detalle contiene la
 * información sobre un producto específico y la cantidad solicitada.
 */
public record PedidoDTO(
        Long clienteId,
        MetodoPago metodoPago,
        LugarCompra lugarCompra,
        String notas,
        List<DetallePedidoDTO> detalles
) {}
