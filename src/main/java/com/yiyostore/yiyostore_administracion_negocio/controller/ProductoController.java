package com.yiyostore.yiyostore_administracion_negocio.controller;

import com.yiyostore.yiyostore_administracion_negocio.model.entity.Producto;
import com.yiyostore.yiyostore_administracion_negocio.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los
 * productos. Provee endpoints para realizar operaciones CRUD sobre los
 * productos.
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    /**
     * Constructor que inyecta el servicio de productos.
     *
     * @param productoService Servicio para la gestión de productos.
     */
    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Obtiene todos los productos disponibles en el sistema.
     *
     * @return Una lista de objetos {@link Producto}.
     */
    @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    /**
     * Obtiene un producto específico por su ID.
     *
     * @param id Identificador único del producto a obtener.
     * @return Un {@link ResponseEntity} con el producto encontrado o un estado
     * 404 si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo producto en el sistema.
     *
     * @param producto Datos del producto a crear.
     * @return El producto creado.
     */
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.crearProducto(producto);
    }

    /**
     * Actualiza un producto existente en el sistema.
     *
     * @param id Identificador único del producto a actualizar.
     * @param producto Nuevos datos del producto.
     * @return Un {@link ResponseEntity} con el producto actualizado o un estado
     * 404 si no se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.actualizarProducto(id, producto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id Identificador único del producto a eliminar.
     * @return Un {@link ResponseEntity} con un estado 204 si se elimina o un
     * estado 404 si no se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        return productoService.eliminarProducto(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
