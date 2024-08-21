package com.yiyostore.yiyostore_administracion_negocio.service;

import com.yiyostore.yiyostore_administracion_negocio.model.entity.Categoria;
import com.yiyostore.yiyostore_administracion_negocio.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para manejar la lógica de negocio relacionada con las categorías.
 */
@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Obtiene todas las categorías.
     *
     * @return Lista de categorías.
     */
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepository.findAll();
    }

    /**
     * Obtiene una categoría por su ID.
     *
     * @param id ID de la categoría.
     * @return Categoría si existe, Optional vacío si no.
     */
    public Optional<Categoria> obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    /**
     * Crea o actualiza una categoría.
     *
     * @param categoria Categoría a guardar.
     * @return Categoría guardada.
     */
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    /**
     * Elimina una categoría por su ID.
     *
     * @param id ID de la categoría a eliminar.
     */
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}
