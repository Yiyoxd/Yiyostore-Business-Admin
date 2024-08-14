package com.yiyostore.yiyostore_administracion_negocio.service;

import com.yiyostore.yiyostore_administracion_negocio.model.Pedido;
import com.yiyostore.yiyostore_administracion_negocio.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que proporciona operaciones CRUD para la entidad Pedido.
 */
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
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
     * @return El pedido encontrado o null si no existe.
     */
    public Pedido obtenerPedidoPorId(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElse(null);
    }

    /**
     * Crea un nuevo pedido.
     *
     * @param pedido El pedido a crear.
     * @return El pedido creado.
     */
    public Pedido crearPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    /**
     * Actualiza un pedido existente.
     *
     * @param id ID del pedido a actualizar.
     * @param pedido Detalles actualizados del pedido.
     * @return El pedido actualizado.
     */
    public Pedido actualizarPedido(Long id, Pedido pedido) {
        if (pedidoRepository.existsById(id)) {
            pedido.setId(id);
            return pedidoRepository.save(pedido);
        } else {
            return null;
        }
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
}
