package com.yiyostore.yiyostore_administracion_negocio.controller;

import com.yiyostore.yiyostore_administracion_negocio.model.Pedido;
import com.yiyostore.yiyostore_administracion_negocio.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las operaciones relacionadas con los pedidos.
 */
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

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
        Pedido pedido = pedidoService.obtenerPedidoPorId(id);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
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
     * Actualiza un pedido existente.
     *
     * @param id ID del pedido a actualizar.
     * @param pedido Nuevos datos del pedido.
     * @return Respuesta con el pedido actualizado o un estado 404 si no se
     * encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        Pedido updatedPedido = pedidoService.actualizarPedido(id, pedido);
        return updatedPedido != null ? ResponseEntity.ok(updatedPedido) : ResponseEntity.notFound().build();
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
