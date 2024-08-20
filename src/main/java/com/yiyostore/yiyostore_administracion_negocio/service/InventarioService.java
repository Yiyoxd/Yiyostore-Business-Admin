package com.yiyostore.yiyostore_administracion_negocio.service;

import com.yiyostore.yiyostore_administracion_negocio.model.LoteProducto;
import com.yiyostore.yiyostore_administracion_negocio.model.Producto;
import com.yiyostore.yiyostore_administracion_negocio.repository.LoteProductoRepository;
import com.yiyostore.yiyostore_administracion_negocio.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio que maneja la lógica de inventario, incluyendo verificación de
 * stock, despacho de productos y cálculos relacionados con lotes.
 */
@Service
public class InventarioService {

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Verifica si hay suficiente stock para cubrir la cantidad solicitada.
     *
     * @param producto El producto a verificar.
     * @param cantidadSolicitada La cantidad solicitada.
     * @return true si hay suficiente stock, false en caso contrario.
     */
    public boolean verificarStockSuficiente(Producto producto, int cantidadSolicitada) {
        if (producto == null || cantidadSolicitada <= 0) {
            return false;
        }

        int cantidadTotalDisponible = producto.calcularCantidadTotal();
        return cantidadTotalDisponible >= cantidadSolicitada;
    }

    /**
     * Calcula el costo total del inventario sumando el costo de todos los lotes
     * de todos los productos en el inventario.
     *
     * @return El costo total del inventario.
     */
    public double calcularCostoTotalInventario() {
        double costoTotalInventario = 0.0;

        List<Producto> productos = productoRepository.findAll();

        for (Producto producto : productos) {
            for (LoteProducto lote : producto.getLotes()) {
                costoTotalInventario += lote.getCosto() * lote.getCantidad();
            }
        }

        return costoTotalInventario;
    }
    
}
