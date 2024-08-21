package com.yiyostore.yiyostore_administracion_negocio.service;

import com.yiyostore.yiyostore_administracion_negocio.model.entity.Producto;
import com.yiyostore.yiyostore_administracion_negocio.model.entity.LoteProducto;
import com.yiyostore.yiyostore_administracion_negocio.model.entity.Pedido;
import com.yiyostore.yiyostore_administracion_negocio.model.entity.DetallePedido;
import com.yiyostore.yiyostore_administracion_negocio.exception.InsufficientStockException;
import com.yiyostore.yiyostore_administracion_negocio.model.*;
import com.yiyostore.yiyostore_administracion_negocio.repository.LoteProductoRepository;
import com.yiyostore.yiyostore_administracion_negocio.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que maneja la lógica de negocio relacionada con los pedidos,
 * incluyendo la gestión de inventario utilizando la metodología PEPS (Primero
 * en Entrar, Primero en Salir).
 */
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final LoteProductoRepository loteProductoRepository;

    /**
     * Constructor que inyecta las dependencias necesarias para este servicio.
     *
     * @param pedidoRepository Repositorio para la entidad Pedido.
     * @param loteProductoRepository Repositorio para la entidad LoteProducto.
     */
    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, LoteProductoRepository loteProductoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.loteProductoRepository = loteProductoRepository;
    }

    /**
     * Realiza un pedido, aplicando la metodología PEPS para ajustar el
     * inventario y registrando el pedido en la base de datos.
     *
     * @param pedido El pedido a realizar.
     * @return El pedido realizado con los ajustes de inventario aplicados.
     */
    @Transactional
    public Pedido realizarPedido(Pedido pedido) {
        pedido.getDetalles().forEach(this::aplicarPEPS);
        return pedidoRepository.save(pedido);
    }

    /**
     * Actualiza un pedido existente, revertiendo los ajustes de inventario
     * anteriores y aplicando los nuevos conforme a la metodología PEPS.
     *
     * @param pedidoExistente El pedido existente a actualizar.
     * @param pedidoActualizado Los detalles actualizados del pedido.
     * @return El pedido actualizado con los nuevos ajustes de inventario.
     */
    @Transactional
    public Pedido actualizarPedido(Pedido pedidoExistente, Pedido pedidoActualizado) {
        revertirAjusteInventario(pedidoExistente);
        actualizarDetallesPedido(pedidoExistente, pedidoActualizado);
        return realizarPedido(pedidoExistente);
    }

    /**
     * Elimina un pedido por su ID, revertiendo los ajustes de inventario
     * correspondientes antes de la eliminación.
     *
     * @param id El ID del pedido a eliminar.
     * @return true si el pedido se eliminó correctamente, false si no se
     * encontró el pedido.
     */
    @Transactional
    public boolean eliminarPedido(Long id) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
        if (pedidoOpt.isPresent()) {
            revertirAjusteInventario(pedidoOpt.get());
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Reverte los ajustes de inventario realizados por un pedido, devolviendo
     * las cantidades utilizadas a los lotes correspondientes.
     *
     * @param pedido El pedido cuyos ajustes de inventario se deben revertir.
     */
    @Transactional
    public void revertirAjusteInventario(Pedido pedido) {
        pedido.getDetalles().forEach(detalle -> {
            LoteProducto lote = detalle.getLote();
            if (lote != null) {
                lote.setCantidad(lote.getCantidad() + detalle.getCantidad());
                loteProductoRepository.save(lote);
            }
        });
    }

    /**
     * Obtiene una lista de todos los pedidos almacenados en la base de datos.
     *
     * @return Una lista de pedidos.
     */
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }

    /**
     * Busca un pedido por su ID.
     *
     * @param id El ID del pedido a buscar.
     * @return Un Optional que contiene el pedido si se encuentra, o vacío si no
     * se encuentra.
     */
    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    /**
     * Aplica la metodología PEPS para ajustar el inventario de un detalle de
     * pedido. Este proceso reduce las cantidades de los lotes en el orden en
     * que fueron adquiridos.
     *
     * @param detalle El detalle del pedido que contiene el producto y la
     * cantidad solicitada.
     */
    private void aplicarPEPS(DetallePedido detalle) {
        Producto producto = detalle.getLote().getProducto();
        int cantidadSolicitada = detalle.getCantidad();
        List<LoteProducto> lotes = obtenerLotesOrdenadosPorFecha(producto);

        for (LoteProducto lote : lotes) {
            if (cantidadSolicitada <= 0) {
                break;
            }
            cantidadSolicitada -= ajustarLote(detalle, lote, cantidadSolicitada);
        }

        if (cantidadSolicitada > 0) {
            throw new InsufficientStockException("No hay suficiente stock disponible para el producto: " + producto.getNombre());
        }
    }

    /**
     * Obtiene una lista de lotes de un producto, ordenados por fecha de
     * adquisición en orden ascendente.
     *
     * @param producto El producto del cual obtener los lotes.
     * @return Una lista de lotes ordenados por fecha de adquisición.
     */
    private List<LoteProducto> obtenerLotesOrdenadosPorFecha(Producto producto) {
        return loteProductoRepository.findByProductoAndEstadoInOrderByFechaAsc(producto, List.of(Estado.NUEVO, Estado.REACONDICIONADO));
    }

    /**
     * Ajusta la cantidad disponible en un lote específico para satisfacer una
     * parte o la totalidad de la cantidad solicitada en un detalle de pedido.
     *
     * @param detalle El detalle del pedido que solicita el producto.
     * @param lote El lote del cual se descontará la cantidad solicitada.
     * @param cantidadSolicitada La cantidad que se debe satisfacer.
     * @return La cantidad realmente descontada del lote.
     */
    private int ajustarLote(DetallePedido detalle, LoteProducto lote, int cantidadSolicitada) {
        int cantidadDisponible = lote.getCantidad();
        int cantidadADescontar = Math.min(cantidadSolicitada, cantidadDisponible);

        lote.setCantidad(cantidadDisponible - cantidadADescontar);

        if (detalle.getLote() == null) {
            detalle.setLote(lote);
        } else {
            crearDetalleAdicional(detalle, lote, cantidadADescontar);
        }

        return cantidadADescontar;
    }

    /**
     * Crea un detalle de pedido adicional si el producto solicitado debe
     * satisfacerse con múltiples lotes.
     *
     * @param detalle El detalle de pedido original.
     * @param lote El lote adicional que se utilizará.
     * @param cantidadADescontar La cantidad que se descontará del lote
     * adicional.
     */
    private void crearDetalleAdicional(DetallePedido detalle, LoteProducto lote, int cantidadADescontar) {
        DetallePedido nuevoDetalle = new DetallePedido(detalle.getPedido(), lote, cantidadADescontar, detalle.getPrecioUnitario());
        detalle.getPedido().agregarDetalle(nuevoDetalle);
    }

    /**
     * Actualiza los detalles de un pedido existente con los detalles de un
     * pedido actualizado. Se eliminan los detalles existentes y se reemplazan
     * por los nuevos.
     *
     * @param pedidoExistente El pedido que se va a actualizar.
     * @param pedidoActualizado El pedido con los nuevos detalles.
     */
    private void actualizarDetallesPedido(Pedido pedidoExistente, Pedido pedidoActualizado) {
        pedidoExistente.getDetalles().clear();
        pedidoActualizado.getDetalles().forEach(pedidoExistente::agregarDetalle);
        pedidoExistente.setCliente(pedidoActualizado.getCliente());
        pedidoExistente.setFecha(pedidoActualizado.getFecha());
        pedidoExistente.setMetodoPago(pedidoActualizado.getMetodoPago());
        pedidoExistente.setLugarCompra(pedidoActualizado.getLugarCompra());
        pedidoExistente.setNotas(pedidoActualizado.getNotas());
        pedidoExistente.setEstado(pedidoActualizado.getEstado());
    }
}
