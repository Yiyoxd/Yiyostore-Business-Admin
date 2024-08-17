package com.yiyostore.yiyostore_administracion_negocio.service;

import com.yiyostore.yiyostore_administracion_negocio.model.Producto;
import com.yiyostore.yiyostore_administracion_negocio.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de productos. Proporciona operaciones CRUD y lógica
 * adicional para la manipulación de objetos {@link Producto}.
 */
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    /**
     * Constructor que inyecta el repositorio de productos.
     *
     * @param productoRepository Repositorio para la manipulación de datos de
     * productos.
     */
    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /**
     * Obtiene una lista de todos los productos disponibles.
     *
     * @return Lista de objetos {@link Producto}.
     */
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    /**
     * Busca un producto por su identificador único.
     *
     * @param id Identificador único del producto.
     * @return Un {@link Optional} que contiene el producto si se encuentra, o
     * vacío si no.
     */
    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    /**
     * Guarda un nuevo producto en la base de datos.
     *
     * @param producto El objeto {@link Producto} a guardar.
     * @return El producto guardado con su ID generado.
     */
    @Transactional
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Actualiza un producto existente en la base de datos. Este método recibe
     * el ID del producto a actualizar, carga el producto desde la base de
     * datos, y aplica las actualizaciones necesarias.
     *
     * @param id Identificador único del producto a actualizar.
     * @param updatedProducto El objeto {@link Producto} con los nuevos valores.
     * @return El producto actualizado, envuelto en un {@link Optional}.
     */
    @Transactional
    public Optional<Producto> actualizarProducto(Long id, Producto updatedProducto) {
        return productoRepository.findById(id).map(existingProducto -> {
            existingProducto.setNombre(updatedProducto.getNombre());
            existingProducto.setDescripcion(updatedProducto.getDescripcion());
            existingProducto.setPrecio(updatedProducto.getPrecio());
            existingProducto.setLotes(updatedProducto.getLotes());
            existingProducto.setFechaDeAdicion(updatedProducto.getFechaDeAdicion());
            return productoRepository.save(existingProducto);
        });
    }

    /**
     * Elimina un producto de la base de datos por su identificador único. Este
     * método solo requiere el ID del producto y no necesita cargar el objeto
     * completo, lo que lo hace más eficiente.
     *
     * @param id Identificador único del producto a eliminar.
     * @return true si el producto fue eliminado, false si no se encontró.
     */
    @Transactional
    public boolean eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Calcula la cantidad total disponible de un producto sumando las
     * cantidades de todos sus lotes. Este método utiliza el objeto
     * {@link Producto} completo para realizar la operación, lo que es eficiente
     * si el producto ya está cargado en memoria.
     *
     * @param idProducto Identificador único del producto.
     * @return La cantidad total disponible del producto.
     */
    public int obtenerCantidadTotalDisponible(Long idProducto) {
        return productoRepository.findById(idProducto)
                .map(Producto::calcularCantidadTotal)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + idProducto));
    }

    /**
     * Calcula el costo promedio ponderado del producto basado en los lotes
     * disponibles. Este método utiliza el objeto {@link Producto} completo para
     * realizar el cálculo del costo.
     *
     * @param idProducto Identificador único del producto.
     * @return El costo promedio ponderado del producto.
     */
    public double calcularCostoPromedioPonderado(Long idProducto) {
        return productoRepository.findById(idProducto)
                .map(Producto::calcularCostoPromedioPonderado)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + idProducto));
    }
}
