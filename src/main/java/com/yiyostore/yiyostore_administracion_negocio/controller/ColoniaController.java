package com.yiyostore.yiyostore_administracion_negocio.controller;

import com.yiyostore.yiyostore_administracion_negocio.model.dto.ColoniaDTO;
import com.yiyostore.yiyostore_administracion_negocio.model.entity.Colonia;
import com.yiyostore.yiyostore_administracion_negocio.service.ColoniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @return ResponseEntity con la colonia actualizada o un estado de error.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Colonia> updateColonia(@PathVariable Long id, @RequestBody Colonia colonia) {
        return coloniaService.findById(id)
                .map(existingColonia -> {
                    colonia.setId(id);
                    Colonia updatedColonia = coloniaService.save(colonia);
                    return ResponseEntity.ok(updatedColonia);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Elimina una colonia por su ID.
     *
     * @param id ID de la colonia a eliminar.
     * @return Respuesta de estado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColonia(@PathVariable Long id) {
        if (coloniaService.existsById(id)) {
            coloniaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/{nombre}")
    public ResponseEntity<List<ColoniaDTO>> searchColonias(@PathVariable String nombre) {
        List<ColoniaDTO> colonias = coloniaService.searchColonias(nombre);
        return ResponseEntity.ok(colonias);
    }

}
