package com.yiyostore.yiyostore_administracion_negocio.service;

import com.yiyostore.yiyostore_administracion_negocio.model.Producto;
import com.yiyostore.yiyostore_administracion_negocio.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio para gestionar las operaciones relacionadas con los productos en la
 * aplicación.
 */
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Obtiene todos los productos almacenados en el repositorio.
     *
     * @return Lista de todos los productos.
     */
    @Transactional(readOnly = true)
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    /**
     * Obtiene un producto específico por su ID.
     *
     * @param id ID del producto a buscar.
     * @return El producto si se encuentra, o null si no existe.
     */
    @Transactional(readOnly = true)
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    /**
     * Crea un nuevo producto en el repositorio.
     *
     * @param producto Datos del producto a crear.
     * @return El producto creado.
     */
    @Transactional
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Actualiza un producto existente en el repositorio.
     *
     * @param id ID del producto a actualizar.
     * @param producto Nuevos datos del producto.
     * @return El producto actualizado, o null si no existe.
     */
    @Transactional
    public Producto actualizarProducto(Long id, Producto producto) {
        if (productoRepository.existsById(id)) {
            producto.setId(id);
            return productoRepository.save(producto);
        } else {
            return null;
        }
    }

    /**
     * Elimina un producto específico por su ID.
     *
     * @param id ID del producto a eliminar.
     * @return true si el producto se eliminó, false si no se encontró.
     */
    @Transactional
    public boolean eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
