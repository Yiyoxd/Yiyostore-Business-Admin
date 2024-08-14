package com.yiyostore.yiyostore_administracion_negocio.controller;

import com.yiyostore.yiyostore_administracion_negocio.model.ProductoPedido;
import com.yiyostore.yiyostore_administracion_negocio.service.ProductoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para manejar las operaciones relacionadas con los productos
 * pedidos.
 */
@RestController
@RequestMapping("/api/productos-pedidos")
public class ProductoPedidoController {

    @Autowired
    private ProductoPedidoService productoPedidoService;

    /**
     * Obtiene la lista de todos los productos pedidos.
     *
     * @return Lista de productos pedidos.
     */
    @GetMapping
    public List<ProductoPedido> getAllProductosPedidos() {
        return productoPedidoService.findAll();
    }

    /**
     * Obtiene un producto pedido por su ID.
     *
     * @param id ID del producto pedido.
     * @return Respuesta con el producto pedido encontrado o un estado 404 si no
     * se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductoPedido> getProductoPedidoById(@PathVariable Long id) {
        Optional<ProductoPedido> productoPedido = productoPedidoService.findById(id);
        return productoPedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo producto pedido.
     *
     * @param productoPedido Datos del producto pedido a crear.
     * @return El producto pedido creado.
     */
    @PostMapping
    public ProductoPedido createProductoPedido(@RequestBody ProductoPedido productoPedido) {
        return productoPedidoService.save(productoPedido);
    }

    /**
     * Actualiza un producto pedido existente.
     *
     * @param id ID del producto pedido a actualizar.
     * @param productoPedido Datos del producto pedido a actualizar.
     * @return El producto pedido actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductoPedido> updateProductoPedido(@PathVariable Long id, @RequestBody ProductoPedido productoPedido) {
        Optional<ProductoPedido> existingProductoPedido = productoPedidoService.findById(id);
        if (existingProductoPedido.isPresent()) {
            productoPedido.setId(id);
            return ResponseEntity.ok(productoPedidoService.save(productoPedido));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un producto pedido por su ID.
     *
     * @param id ID del producto pedido a eliminar.
     * @return Respuesta de estado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductoPedido(@PathVariable Long id) {
        productoPedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
