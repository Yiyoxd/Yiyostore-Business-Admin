package com.yiyostore.yiyostore_administracion_negocio.controller;

import com.yiyostore.yiyostore_administracion_negocio.model.Colonia;
import com.yiyostore.yiyostore_administracion_negocio.service.ColoniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para manejar las operaciones relacionadas con las Colonias.
 */
@RestController
@RequestMapping("/api/colonias")
public class ColoniaController {

    @Autowired
    private ColoniaService coloniaService;

    /**
     * Obtiene la lista de todas las colonias.
     *
     * @return Lista de colonias.
     */
    @GetMapping
    public List<Colonia> getAllColonias() {
        return coloniaService.findAll();
    }

    /**
     * Obtiene una colonia por su ID.
     *
     * @param id ID de la colonia.
     * @return Respuesta con la colonia encontrada o un estado 404 si no se
     * encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Colonia> getColoniaById(@PathVariable Long id) {
        Optional<Colonia> colonia = coloniaService.findById(id);
        return colonia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crea una nueva colonia.
     *
     * @param colonia Datos de la colonia a crear.
     * @return La colonia creada.
     */
    @PostMapping
    public Colonia createColonia(@RequestBody Colonia colonia) {
        return coloniaService.save(colonia);
    }

    /**
     * Actualiza una colonia existente.
     *
     * @param id ID de la colonia a actualizar.
     * @param colonia Datos de la colonia a actualizar.
     * @return La colonia actualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Colonia> updateColonia(@PathVariable Long id, @RequestBody Colonia colonia) {
        Optional<Colonia> existingColonia = coloniaService.findById(id);
        if (existingColonia.isPresent()) {
            colonia.setId(id);
            return ResponseEntity.ok(coloniaService.save(colonia));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina una colonia por su ID.
     *
     * @param id ID de la colonia a eliminar.
     * @return Respuesta de estado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColonia(@PathVariable Long id) {
        coloniaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
