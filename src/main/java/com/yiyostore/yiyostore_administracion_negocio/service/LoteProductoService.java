package com.yiyostore.yiyostore_administracion_negocio.service;

import com.yiyostore.yiyostore_administracion_negocio.model.entity.LoteProducto;
import com.yiyostore.yiyostore_administracion_negocio.repository.LoteProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que proporciona operaciones CRUD para la entidad LoteProducto.
 */
@Service
public class LoteProductoService {

    private final LoteProductoRepository loteProductoRepository;

    @Autowired
    public LoteProductoService(LoteProductoRepository loteProductoRepository) {
        this.loteProductoRepository = loteProductoRepository;
    }

    /**
     * Obtiene todos los lotes de productos.
     *
     * @return Lista de todos los lotes de productos.
     */
    public List<LoteProducto> obtenerTodosLosLotes() {
        return loteProductoRepository.findAll();
    }

    /**
     * Obtiene un lote de producto por su ID.
     *
     * @param id ID del lote de producto.
     * @return El lote de producto encontrado o null si no existe.
     */
    public LoteProducto obtenerLotePorId(Long id) {
        Optional<LoteProducto> lote = loteProductoRepository.findById(id);
        return lote.orElse(null);
    }

    /**
     * Crea un nuevo lote de producto.
     *
     * @param lote El lote de producto a crear.
     * @return El lote de producto creado.
     */
    public LoteProducto crearLote(LoteProducto lote) {
        return loteProductoRepository.save(lote);
    }

    /**
     * Actualiza un lote de producto existente.
     *
     * @param id ID del lote de producto a actualizar.
     * @param lote Detalles actualizados del lote de producto.
     * @return El lote de producto actualizado.
     */
    public LoteProducto actualizarLote(Long id, LoteProducto lote) {
        if (loteProductoRepository.existsById(id)) {
            lote.setId(id);
            return loteProductoRepository.save(lote);
        } else {
            return null;
        }
    }

    /**
     * Elimina un lote de producto por su ID.
     *
     * @param id ID del lote de producto a eliminar.
     * @return true si el lote de producto fue eliminado, false si no se
     * encontró.
     */
    public boolean eliminarLote(Long id) {
        if (loteProductoRepository.existsById(id)) {
            loteProductoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
