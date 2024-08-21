package com.yiyostore.yiyostore_administracion_negocio.controller;

import com.yiyostore.yiyostore_administracion_negocio.model.entity.Ciudad;
import com.yiyostore.yiyostore_administracion_negocio.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para manejar las operaciones relacionadas con la entidad
 * Ciudad.
 */
@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {

    private final CiudadService ciudadService;

    @Autowired
    public CiudadController(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    /**
     * Endpoint para obtener todas las ciudades.
     *
     * @return Una lista de todas las ciudades.
     */
    @GetMapping
    public List<Ciudad> getAllCiudades() {
        return ciudadService.getAllCiudades();
    }

    /**
     * Endpoint para obtener una ciudad por su ID.
     *
     * @param id El ID de la ciudad.
     * @return La ciudad correspondiente al ID, o un 404 si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> getCiudadById(@PathVariable Long id) {
        Optional<Ciudad> ciudad = ciudadService.getCiudadById(id);
        return ciudad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para crear una nueva ciudad.
     *
     * @param ciudad La ciudad a crear.
     * @return La ciudad creada.
     */
    @PostMapping
    public Ciudad createCiudad(@RequestBody Ciudad ciudad) {
        return ciudadService.saveCiudad(ciudad);
    }

    /**
     * Endpoint para actualizar una ciudad existente.
     *
     * @param id El ID de la ciudad a actualizar.
     * @param ciudad Los nuevos datos de la ciudad.
     * @return La ciudad actualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Ciudad> updateCiudad(@PathVariable Long id, @RequestBody Ciudad ciudad) {
        if (!ciudadService.getCiudadById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ciudad.setId(id);
        Ciudad updatedCiudad = ciudadService.saveCiudad(ciudad);
        return ResponseEntity.ok(updatedCiudad);
    }

    /**
     * Endpoint para eliminar una ciudad por su ID.
     *
     * @param id El ID de la ciudad a eliminar.
     * @return Una respuesta vacía indicando que la operación fue exitosa.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCiudad(@PathVariable Long id) {
        if (!ciudadService.getCiudadById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ciudadService.deleteCiudad(id);
        return ResponseEntity.noContent().build();
    }
}
