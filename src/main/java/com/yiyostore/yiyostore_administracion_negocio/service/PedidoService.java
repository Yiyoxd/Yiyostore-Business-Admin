package com.yiyostore.yiyostore_administracion_negocio.service;

import com.yiyostore.yiyostore_administracion_negocio.model.Pedido;
import com.yiyostore.yiyostore_administracion_negocio.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio que proporciona operaciones CRUD para la entidad Pedido y maneja la
 * lógica de procesamiento de pedidos.
 */
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, InventarioService inventarioService) {
        this.pedidoRepository = pedidoRepository;
    }

    /**
     * Obtiene todos los pedidos.
     *
     * @return Lista de todos los pedidos.
     */
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }

    /**
     * Obtiene un pedido por su ID.
     *
     * @param id ID del pedido.
     * @return Un Optional que contiene el pedido si se encuentra, o un Optional
     * vacío si no.
     */
    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    /**
     * Crea un nuevo pedido.
     *
     * @param pedido El pedido a crear.
     * @return El pedido creado.
     */
    public Pedido crearPedido(Pedido pedido) {
        return guardarPedido(pedido);
    }

    /**
     * Elimina un pedido por su ID.
     *
     * @param id ID del pedido a eliminar.
     * @return true si el pedido fue eliminado, false si no se encontró.
     */
    public boolean eliminarPedido(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Guarda un pedido en el repositorio.
     *
     * @param pedido El pedido a guardar.
     * @return El pedido guardado.
     */
    private Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
}
