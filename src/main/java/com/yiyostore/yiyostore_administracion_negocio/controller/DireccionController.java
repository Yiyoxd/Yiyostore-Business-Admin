package com.yiyostore.yiyostore_administracion_negocio.controller;

import com.yiyostore.yiyostore_administracion_negocio.model.Direccion;
import com.yiyostore.yiyostore_administracion_negocio.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las operaciones relacionadas con las
 * direcciones.
 */
@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    /**
     * Obtiene la lista de todas las direcciones.
     *
     * @return Lista de direcciones.
     */
    @GetMapping
    public List<Direccion> getAllDirecciones() {
        return direccionService.obtenerTodasLasDirecciones();
    }

    /**
     * Obtiene una dirección por su ID.
     *
     * @param id ID de la dirección.
     * @return Respuesta con la dirección encontrada o un estado 404 si no se
     * encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Direccion> getDireccionById(@PathVariable Long id) {
        Direccion direccion = direccionService.obtenerDireccionPorId(id);
        return direccion != null ? ResponseEntity.ok(direccion) : ResponseEntity.notFound().build();
    }

    /**
     * Crea una nueva dirección.
     *
     * @param direccion Datos de la dirección a crear.
     * @return La dirección creada.
     */
    @PostMapping
    public Direccion createDireccion(@RequestBody Direccion direccion) {
        return direccionService.crearDireccion(direccion);
    }

    /**
     * Actualiza una dirección existente.
     *
     * @param id ID de la dirección a actualizar.
     * @param direccion Nuevos datos de la dirección.
     * @return Respuesta con la dirección actualizada o un estado 404 si no se
     * encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Direccion> updateDireccion(@PathVariable Long id, @RequestBody Direccion direccion) {
        Direccion updatedDireccion = direccionService.actualizarDireccion(id, direccion);
        return updatedDireccion != null ? ResponseEntity.ok(updatedDireccion) : ResponseEntity.notFound().build();
    }

    /**
     * Elimina una dirección por su ID.
     *
     * @param id ID de la dirección a eliminar.
     * @return Respuesta con un estado 204 si se elimina o un estado 404 si no
     * se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDireccion(@PathVariable Long id) {
        boolean deleted = direccionService.eliminarDireccion(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
