package com.yiyostore.yiyostore_administracion_negocio.service;


import com.yiyostore.yiyostore_administracion_negocio.model.Colonia;
import com.yiyostore.yiyostore_administracion_negocio.repository.ColoniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para manejar la lógica de negocio relacionada con las Colonias.
 */
@Service
public class ColoniaService {

    @Autowired
    private ColoniaRepository coloniaRepository;

    /**
     * Obtiene todas las colonias almacenadas.
     *
     * @return Lista de todas las colonias.
     */
    public List<Colonia> findAll() {
        return coloniaRepository.findAll();
    }

    /**
     * Busca una colonia por su ID.
     *
     * @param id Identificador de la colonia.
     * @return Un Optional que contiene la colonia si se encuentra, de lo contrario vacío.
     */
    public Optional<Colonia> findById(Long id) {
        return coloniaRepository.findById(id);
    }

    /**
     * Guarda una nueva colonia o actualiza una existente.
     *
     * @param colonia La colonia a guardar.
     * @return La colonia guardada.
     */
    public Colonia save(Colonia colonia) {
        return coloniaRepository.save(colonia);
    }

    /**
     * Elimina una colonia por su ID.
     *
     * @param id Identificador de la colonia a eliminar.
     */
    public void deleteById(Long id) {
        coloniaRepository.deleteById(id);
    }
}
