package com.yiyostore.yiyostore_administracion_negocio.service;

import com.yiyostore.yiyostore_administracion_negocio.model.dto.ProductoInventarioDTO;
import com.yiyostore.yiyostore_administracion_negocio.model.entity.Producto;
import com.yiyostore.yiyostore_administracion_negocio.model.enums.Estado;
import com.yiyostore.yiyostore_administracion_negocio.repository.LoteProductoRepository;
import com.yiyostore.yiyostore_administracion_negocio.repository.ProductoRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio que maneja la lógica de inventario, incluyendo verificación de
 * stock, despacho de productos y cálculos relacionados con lotes.
 */
@Service
public class InventarioService {

    private final LoteProductoRepository loteProductoRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public InventarioService(LoteProductoRepository loteProductoRepository, ProductoRepository productoRepository) {
        this.loteProductoRepository = loteProductoRepository;
        this.productoRepository = productoRepository;
    }

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

        return false;
    }

    /**
     * Calcula el costo total del inventario sumando el costo de todos los lotes
     * de todos los productos en el inventario.
     *
     * @return El costo total del inventario.
     */
    public double calcularCostoTotalInventario() {
        return loteProductoRepository.calcularCostoTotalInventario();
    }

    /**
     * Calcula el precio de venta total del inventario sumando el precio de
     * todos los lotes de todos los productos en el inventario.
     *
     * @return El costo total del inventario.
     */
    public double calcularPrecioVentaTotalInventario() {
        return productoRepository.calcularPrecioVentaTotalInventario();
    }

    /**
     * Obtiene un resumen del inventario que incluye el ID del producto, el
     * nombre del producto y las cantidades disponibles por estado. Solo se
     * muestran los estados con cantidades mayores a cero.
     *
     * @return Una lista de objetos {@link ProductoInventarioDTO} que contienen
     * el resumen del inventario.
     */
    public List<ProductoInventarioDTO> obtenerResumenInventario() {
        return productoRepository.findAllWithLotesHavingQuantityGreaterThanZeroOrNoLotes().stream().map(producto -> {
            Map<String, Integer> cantidadesPorEstado = new HashMap<>();
            producto.getLotes().forEach(lote
                    -> cantidadesPorEstado.merge(lote.getEstado().getDisplayName(), lote.getCantidad(), Integer::sum)
            );

            if (cantidadesPorEstado.isEmpty()) {
                cantidadesPorEstado.put("Sin Stock", 0);
            }
            return new ProductoInventarioDTO(producto.getId(), producto.getNombre(), cantidadesPorEstado);
        }).collect(Collectors.toList());
    }
}
