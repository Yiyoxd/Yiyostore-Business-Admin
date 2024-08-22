package com.yiyostore.yiyostore_administracion_negocio.service;

import com.yiyostore.yiyostore_administracion_negocio.exception.InsufficientStockException;
import com.yiyostore.yiyostore_administracion_negocio.model.Estado;
import com.yiyostore.yiyostore_administracion_negocio.model.EstadoPedido;
import com.yiyostore.yiyostore_administracion_negocio.model.dto.DetallePedidoDTO;
import com.yiyostore.yiyostore_administracion_negocio.model.dto.PedidoDTO;
import com.yiyostore.yiyostore_administracion_negocio.model.entity.Cliente;
import com.yiyostore.yiyostore_administracion_negocio.model.entity.DetallePedido;
import com.yiyostore.yiyostore_administracion_negocio.model.entity.LoteProducto;
import com.yiyostore.yiyostore_administracion_negocio.model.entity.Pedido;
import com.yiyostore.yiyostore_administracion_negocio.model.entity.Producto;
import com.yiyostore.yiyostore_administracion_negocio.repository.ClienteRepository;
import com.yiyostore.yiyostore_administracion_negocio.repository.LoteProductoRepository;
import com.yiyostore.yiyostore_administracion_negocio.repository.PedidoRepository;
import com.yiyostore.yiyostore_administracion_negocio.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;

    /**
     * Constructor que inyecta las dependencias necesarias para este servicio.
     *
     * @param pedidoRepository Repositorio para la entidad Pedido.
     * @param loteProductoRepository Repositorio para la entidad LoteProducto.
     * @param clienteRepository Repositorio para la entidad Cliente.
     * @param productoRepository Repositorio para la entidad Producto.
     */
    @Autowired
    public PedidoService(
            PedidoRepository pedidoRepository,
            LoteProductoRepository loteProductoRepository,
            ClienteRepository clienteRepository,
            ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.loteProductoRepository = loteProductoRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    /**
     * Realiza un pedido basado en un PedidoDTO.
     *
     * @param pedidoDTO el objeto DTO que contiene los datos necesarios para
     * crear un pedido.
     * @return el Pedido creado y guardado en la base de datos.
     */
    @Transactional
    public Pedido realizarPedidoDTO(PedidoDTO pedidoDTO) {
        List<DetallePedido> detalles = crearDetallesDesdeDTO(pedidoDTO.detalles());
        Cliente cliente = clienteRepository.findById(pedidoDTO.clienteId()).orElseThrow();
        
        Pedido pedido = new Pedido(
                cliente,
                pedidoDTO.fecha(),
                detalles,
                pedidoDTO.metodoPago(),
                pedidoDTO.lugarCompra(),
                pedidoDTO.notas(),
                EstadoPedido.PENDIENTE
        );
        
        detalles.forEach(detalle -> detalle.cambiarDePedidoSinVerificar(pedido));
        
        return pedidoRepository.save(pedido);
    }

    /**
     * Convierte una lista de DetallePedidoDTO en una lista de DetallePedido.
     *
     * @param detallesDTO la lista de objetos DTO que representan los detalles
     * del pedido.
     * @return una lista de objetos DetallePedido.
     */
    private List<DetallePedido> crearDetallesDesdeDTO(List<DetallePedidoDTO> detallesDTO) {
        List<DetallePedido> detalles = new ArrayList<>();
        detallesDTO.forEach(dto -> detalles.addAll(crearDetallesProductoDesdeDTO(dto)));
        return detalles;
    }

    /**
     * Convierte un DetallePedidoDTO en una lista de DetallePedido asociados con
     * un producto.
     *
     * @param detallePedidoDTO el DTO que contiene la información del producto y
     * cantidad.
     * @return una lista de objetos DetallePedido.
     */
    private List<DetallePedido> crearDetallesProductoDesdeDTO(DetallePedidoDTO detallePedidoDTO) {
        Producto producto = productoRepository.findById(detallePedidoDTO.idProducto()).orElseThrow();
        int cantidad = detallePedidoDTO.cantidad();

        return crearDetallesProducto(producto, cantidad);
    }

    /**
     * Crea una lista de DetallePedido para un producto específico,
     * distribuyendo la cantidad entre los lotes disponibles.
     *
     * @param producto el producto para el cual se crearán los detalles del
     * pedido.
     * @param cantidad la cantidad solicitada del producto.
     * @return una lista de objetos DetallePedido.
     * @throws InsufficientStockException si no hay suficiente stock disponible
     * para cumplir con el pedido.
     */
    public List<DetallePedido> crearDetallesProducto(Producto producto, int cantidad) {
        List<LoteProducto> lotes = obtenerLotesOrdenadosPorFecha(producto);
        List<DetallePedido> detalles = new ArrayList<>();

        for (LoteProducto lote : lotes) {
            if (cantidad <= 0) {
                break;
            }
            int cantidadADescontar = ajustarLote(lote, cantidad);
            cantidad -= cantidadADescontar;

            DetallePedido detalle = new DetallePedido(null, lote, cantidadADescontar, producto.getPrecio());
            detalles.add(detalle);
        }

        if (cantidad > 0) {
            throw new InsufficientStockException("Stock insuficiente para el producto " + producto.getNombre());
        }

        return detalles;
    }

    /**
     * Ajusta la cantidad de un lote específico, descontando la cantidad
     * utilizada.
     *
     * @param lote el lote a ajustar.
     * @param cantidad la cantidad a descontar del lote.
     */
    private int ajustarLote(LoteProducto lote, int cantidad) {
        int cantidadADescontar = Math.min(cantidad, lote.getCantidad());
        lote.setCantidad(lote.getCantidad() - cantidadADescontar);
        loteProductoRepository.save(lote);

        return cantidadADescontar;
    }

    /**
     * Actualiza un pedido existente, revertiendo los ajustes de inventario
     * anteriores y aplicando los nuevos.
     *
     * @param pedidoExistente el pedido existente a actualizar.
     * @param pedidoActualizado los detalles actualizados del pedido.
     * @return el pedido actualizado con los nuevos ajustes de inventario.
     */
    @Transactional
    public Pedido actualizarPedido(Pedido pedidoExistente, Pedido pedidoActualizado) {
        revertirAjusteInventario(pedidoExistente);
        actualizarDetallesPedido(pedidoExistente, pedidoActualizado);
        return pedidoRepository.save(pedidoExistente);
    }

    /**
     * Elimina un pedido por su ID, revertiendo los ajustes de inventario
     * correspondientes antes de la eliminación.
     *
     * @param id el ID del pedido a eliminar.
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
     * Revierte los ajustes de inventario realizados por un pedido, devolviendo
     * las cantidades utilizadas a los lotes correspondientes.
     *
     * @param pedido el pedido cuyos ajustes de inventario se deben revertir.
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
     * @return una lista de pedidos.
     */
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }

    /**
     * Busca un pedido por su ID.
     *
     * @param id el ID del pedido a buscar.
     * @return un Optional que contiene el pedido si se encuentra, o vacío si no
     * se encuentra.
     */
    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    /**
     * Obtiene una lista de lotes de un producto, ordenados por fecha de
     * adquisición en orden ascendente.
     *
     * @param producto el producto del cual obtener los lotes.
     * @return una lista de lotes ordenados por fecha de adquisición.
     */
    private List<LoteProducto> obtenerLotesOrdenadosPorFecha(Producto producto) {
        return loteProductoRepository.findByProductoAndEstadoInOrderByFechaAsc(producto, List.of(Estado.NUEVO, Estado.REACONDICIONADO));
    }

    /**
     * Actualiza los detalles de un pedido existente con los detalles de un
     * pedido actualizado. Se eliminan los detalles existentes y se reemplazan
     * por los nuevos.
     *
     * @param pedidoExistente el pedido que se va a actualizar.
     * @param pedidoActualizado el pedido con los nuevos detalles.
     */
    private void actualizarDetallesPedido(Pedido pedidoExistente, Pedido pedidoActualizado) {
        pedidoExistente.getDetalles().clear();
        pedidoActualizado.setDetalles(pedidoActualizado.getDetalles());
        pedidoExistente.setCliente(pedidoActualizado.getCliente());
        pedidoExistente.setFecha(pedidoActualizado.getFecha());
        pedidoExistente.setMetodoPago(pedidoActualizado.getMetodoPago());
        pedidoExistente.setLugarCompra(pedidoActualizado.getLugarCompra());
        pedidoExistente.setNotas(pedidoActualizado.getNotas());
        pedidoExistente.setEstado(pedidoActualizado.getEstado());
    }
}
