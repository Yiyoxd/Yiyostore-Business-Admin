package com.yiyostore.yiyostore_administracion_negocio.service;


import com.yiyostore.yiyostore_administracion_negocio.model.ProductoPedido;
import com.yiyostore.yiyostore_administracion_negocio.repository.ProductoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para manejar la lógica de negocio relacionada con los productos pedidos.
 */
@Service
public class ProductoPedidoService {

    @Autowired
    private ProductoPedidoRepository productoPedidoRepository;

    /**
     * Obtiene todos los productos pedidos almacenados.
     *
     * @return Lista de todos los productos pedidos.
     */
    public List<ProductoPedido> findAll() {
        return productoPedidoRepository.findAll();
    }

    /**
     * Busca un producto pedido por su ID.
     *
     * @param id Identificador del producto pedido.
     * @return Un Optional que contiene el producto pedido si se encuentra, de lo contrario vacío.
     */
    public Optional<ProductoPedido> findById(Long id) {
        return productoPedidoRepository.findById(id);
    }

    /**
     * Guarda un nuevo producto pedido o actualiza uno existente.
     *
     * @param productoPedido El producto pedido a guardar.
     * @return El producto pedido guardado.
     */
    public ProductoPedido save(ProductoPedido productoPedido) {
        return productoPedidoRepository.save(productoPedido);
    }

    /**
     * Elimina un producto pedido por su ID.
     *
     * @param id Identificador del producto pedido a eliminar.
     */
    public void deleteById(Long id) {
        productoPedidoRepository.deleteById(id);
    }
}
