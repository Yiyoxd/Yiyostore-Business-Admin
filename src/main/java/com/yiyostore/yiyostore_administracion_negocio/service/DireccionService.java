package com.yiyostore.yiyostore_administracion_negocio.service;

import com.yiyostore.yiyostore_administracion_negocio.model.Direccion;
import com.yiyostore.yiyostore_administracion_negocio.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que proporciona operaciones CRUD para la entidad Direccion.
 */
@Service
public class DireccionService {

    private final DireccionRepository direccionRepository;

    @Autowired
    public DireccionService(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    /**
     * Obtiene todas las direcciones.
     *
     * @return Lista de todas las direcciones.
     */
    public List<Direccion> obtenerTodasLasDirecciones() {
        return direccionRepository.findAll();
    }

    /**
     * Obtiene una dirección por su ID.
     *
     * @param id ID de la dirección.
     * @return La dirección encontrada o null si no existe.
     */
    public Direccion obtenerDireccionPorId(Long id) {
        Optional<Direccion> direccion = direccionRepository.findById(id);
        return direccion.orElse(null);
    }

    /**
     * Crea una nueva dirección.
     *
     * @param direccion La dirección a crear.
     * @return La dirección creada.
     */
    public Direccion crearDireccion(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    /**
     * Actualiza una dirección existente.
     *
     * @param id ID de la dirección a actualizar.
     * @param direccion Detalles actualizados de la dirección.
     * @return La dirección actualizada.
     */
    public Direccion actualizarDireccion(Long id, Direccion direccion) {
        if (direccionRepository.existsById(id)) {
            direccion.setId(id);
            return direccionRepository.save(direccion);
        } else {
            return null;
        }
    }

    /**
     * Elimina una dirección por su ID.
     *
     * @param id ID de la dirección a eliminar.
     * @return true si la dirección fue eliminada, false si no se encontró.
     */
    public boolean eliminarDireccion(Long id) {
        if (direccionRepository.existsById(id)) {
            direccionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
