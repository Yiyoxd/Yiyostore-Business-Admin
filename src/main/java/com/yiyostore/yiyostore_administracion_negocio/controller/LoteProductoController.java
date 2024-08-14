package com.yiyostore.yiyostore_administracion_negocio.controller;

import com.yiyostore.yiyostore_administracion_negocio.model.LoteProducto;
import com.yiyostore.yiyostore_administracion_negocio.service.LoteProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las operaciones relacionadas con los lotes de
 * productos.
 */
@RestController
@RequestMapping("/api/lotes-productos")
public class LoteProductoController {

    @Autowired
    private LoteProductoService loteProductoService;

    /**
     * Obtiene la lista de todos los lotes de productos.
     *
     * @return Lista de lotes de productos.
     */
    @GetMapping
    public List<LoteProducto> getAllLotes() {
        return loteProductoService.obtenerTodosLosLotes();
    }

    /**
     * Obtiene un lote de producto por su ID.
     *
     * @param id ID del lote de producto.
     * @return Respuesta con el lote de producto encontrado o un estado 404 si
     * no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LoteProducto> getLoteById(@PathVariable Long id) {
        LoteProducto loteProducto = loteProductoService.obtenerLotePorId(id);
        return loteProducto != null ? ResponseEntity.ok(loteProducto) : ResponseEntity.notFound().build();
    }

    /**
     * Crea un nuevo lote de producto.
     *
     * @param loteProducto Datos del lote de producto a crear.
     * @return El lote de producto creado.
     */
    @PostMapping
    public LoteProducto createLote(@RequestBody LoteProducto loteProducto) {
        return loteProductoService.crearLote(loteProducto);
    }

    /**
     * Actualiza un lote de producto existente.
     *
     * @param id ID del lote de producto a actualizar.
     * @param loteProducto Nuevos datos del lote de producto.
     * @return Respuesta con el lote de producto actualizado o un estado 404 si
     * no se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LoteProducto> updateLote(@PathVariable Long id, @RequestBody LoteProducto loteProducto) {
        LoteProducto updatedLoteProducto = loteProductoService.actualizarLote(id, loteProducto);
        return updatedLoteProducto != null ? ResponseEntity.ok(updatedLoteProducto) : ResponseEntity.notFound().build();
    }

    /**
     * Elimina un lote de producto por su ID.
     *
     * @param id ID del lote de producto a eliminar.
     * @return Respuesta con un estado 204 si se elimina o un estado 404 si no
     * se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLote(@PathVariable Long id) {
        boolean deleted = loteProductoService.eliminarLote(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
