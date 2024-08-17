package com.yiyostore.yiyostore_administracion_negocio.controller;

import com.yiyostore.yiyostore_administracion_negocio.model.Pedido;
import com.yiyostore.yiyostore_administracion_negocio.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para manejar las operaciones relacionadas con los pedidos.
 */
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    /**
     * Obtiene la lista de todos los pedidos.
     *
     * @return Lista de pedidos.
     */
    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoService.obtenerTodosLosPedidos();
    }

    /**
     * Obtiene un pedido por su ID.
     *
     * @param id ID del pedido.
     * @return Respuesta con el pedido encontrado o un estado 404 si no se
     * encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        Optional<Pedido> pedidoOptional = pedidoService.obtenerPedidoPorId(id);
        return pedidoOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo pedido.
     *
     * @param pedido Datos del pedido a crear.
     * @return El pedido creado.
     */
    @PostMapping
    public Pedido createPedido(@RequestBody Pedido pedido) {
        return pedidoService.crearPedido(pedido);
    }

    /**
     * Elimina un pedido por su ID.
     *
     * @param id ID del pedido a eliminar.
     * @return Respuesta con un estado 204 si se elimina o un estado 404 si no
     * se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        boolean deleted = pedidoService.eliminarPedido(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
