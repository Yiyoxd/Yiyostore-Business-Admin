package com.yiyostore.yiyostore_administracion_negocio.controller;

import com.yiyostore.yiyostore_administracion_negocio.model.Producto;
import com.yiyostore.yiyostore_administracion_negocio.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los
 * productos. Provee endpoints para realizar operaciones CRUD sobre los
 * productos.
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Obtiene todos los productos disponibles en el sistema.
     *
     * @return Lista de productos.
     */
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id ID del producto a obtener.
     * @return ResponseEntity con el producto encontrado o un estado 404 si no
     * se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    /**
     * Crea un nuevo producto en el sistema.
     *
     * @param producto Datos del producto a crear.
     * @return El producto creado.
     */
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoService.crearProducto(producto);
    }

    /**
     * Actualiza un producto existente.
     *
     * @param id ID del producto a actualizar.
     * @param producto Nuevos datos del producto.
     * @return ResponseEntity con el producto actualizado o un estado 404 si no
     * se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto updatedProducto = productoService.actualizarProducto(id, producto);
        return updatedProducto != null ? ResponseEntity.ok(updatedProducto) : ResponseEntity.notFound().build();
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id ID del producto a eliminar.
     * @return ResponseEntity con un estado 204 si se elimina o un estado 404 si
     * no se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        boolean deleted = productoService.eliminarProducto(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
