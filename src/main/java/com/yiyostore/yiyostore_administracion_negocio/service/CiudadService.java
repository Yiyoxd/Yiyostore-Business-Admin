package com.yiyostore.yiyostore_administracion_negocio.service;

import com.yiyostore.yiyostore_administracion_negocio.model.entity.Ciudad;
import com.yiyostore.yiyostore_administracion_negocio.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para manejar la lógica de negocio relacionada con la entidad Ciudad.
 */
@Service
public class CiudadService {

    private final CiudadRepository ciudadRepository;

    @Autowired
    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    /**
     * Obtiene todas las ciudades.
     *
     * @return Una lista de todas las ciudades.
     */
    public List<Ciudad> getAllCiudades() {
        return ciudadRepository.findAll();
    }

    /**
     * Obtiene una ciudad por su ID.
     *
     * @param id El ID de la ciudad.
     * @return Una instancia opcional de la ciudad.
     */
    public Optional<Ciudad> getCiudadById(Long id) {
        return ciudadRepository.findById(id);
    }

    /**
     * Guarda una nueva ciudad o actualiza una existente.
     *
     * @param ciudad La ciudad a guardar.
     * @return La ciudad guardada.
     */
    public Ciudad saveCiudad(Ciudad ciudad) {
        return ciudadRepository.save(ciudad);
    }

    /**
     * Elimina una ciudad por su ID.
     *
     * @param id El ID de la ciudad a eliminar.
     */
    public void deleteCiudad(Long id) {
        ciudadRepository.deleteById(id);
    }
}
