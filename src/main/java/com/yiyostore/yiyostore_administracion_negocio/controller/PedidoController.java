package com.yiyostore.yiyostore_administracion_negocio.controller;

import com.yiyostore.yiyostore_administracion_negocio.model.dto.PedidoDTO;
import com.yiyostore.yiyostore_administracion_negocio.model.entity.Pedido;
import com.yiyostore.yiyostore_administracion_negocio.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar los pedidos.
 */
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    /**
     * Constructor del controlador de pedidos.
     *
     * @param pedidoService Servicio de pedidos.
     */
    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    /**
     * Crea un nuevo pedido aplicando la metodología PEPS para ajustar el
     * inventario.
     *
     * @param pedidoDTO El pedido a crear.
     * @return El pedido creado.
     */
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedidoCreado = pedidoService.realizarPedidoDTO(pedidoDTO);
        return ResponseEntity.ok(pedidoCreado);
    }

    /**
     * Obtiene todos los pedidos.
     *
     * @return Lista de pedidos.
     */
    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodosLosPedidos() {
        List<Pedido> pedidos = pedidoService.obtenerTodosLosPedidos();
        return ResponseEntity.ok(pedidos);
    }

    /**
     * Obtiene un pedido por su ID.
     *
     * @param id ID del pedido.
     * @return El pedido encontrado o 404 si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.obtenerPedidoPorId(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Actualiza un pedido existente y ajusta el inventario utilizando la
     * metodología PEPS.
     *
     * @param id ID del pedido a actualizar.
     * @param pedidoActualizado Detalles actualizados del pedido.
     * @return El pedido actualizado o 404 si no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedidoActualizado) {
        Optional<Pedido> pedidoExistente = pedidoService.obtenerPedidoPorId(id);

        if (pedidoExistente.isPresent()) {
            Pedido pedidoGuardado = pedidoService.actualizarPedido(pedidoExistente.get(), pedidoActualizado);
            return ResponseEntity.ok(pedidoGuardado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un pedido por su ID.
     *
     * @param id ID del pedido a eliminar.
     * @return Respuesta 204 si se eliminó correctamente o 404 si no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        boolean eliminado = pedidoService.eliminarPedido(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
