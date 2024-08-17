package com.yiyostore.yiyostore_administracion_negocio.service;

import com.yiyostore.yiyostore_administracion_negocio.model.LoteProducto;
import com.yiyostore.yiyostore_administracion_negocio.model.Producto;
import com.yiyostore.yiyostore_administracion_negocio.exception.InsufficientStockException;
import com.yiyostore.yiyostore_administracion_negocio.model.Estado;
import com.yiyostore.yiyostore_administracion_negocio.repository.LoteProductoRepository;
import com.yiyostore.yiyostore_administracion_negocio.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

/**
 * Servicio que maneja la lógica de inventario, incluyendo verificación de
 * stock, despacho de productos y cálculos relacionados con lotes.
 */
@Service
public class InventarioService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private LoteProductoRepository loteProductoRepository;

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
     * Despacha una cantidad específica de un producto, siempre y cuando haya
     * suficiente stock.
     *
     * @param producto El producto a despachar.
     * @param cantidadSolicitada La cantidad solicitada.
     * @throws InsufficientStockException Si no hay suficiente stock disponible.
     */
    @Transactional
    public void despacharProducto(Producto producto, int cantidadSolicitada) throws InsufficientStockException {
        if (!verificarStockSuficiente(producto, cantidadSolicitada)) {
            throw new InsufficientStockException("No hay suficiente stock disponible.");
        }

        List<LoteProducto> lotes = loteProductoRepository.findByProductoAndEstadoInOrderByFechaAsc(
                producto, Arrays.asList(Estado.NUEVO, Estado.REACONDICIONADO)
        );

        for (LoteProducto lote : lotes) {
            if (cantidadSolicitada <= 0) {
                break;
            }
            cantidadSolicitada = ajustarCantidadEnLote(lote, cantidadSolicitada);
        }

        if (cantidadSolicitada > 0) {
            throw new InsufficientStockException("No se pudo cubrir la cantidad solicitada con el stock disponible.");
        }
    }

    /**
     * Devuelve la cantidad especificada de un producto al inventario.
     *
     * @param producto El producto a devolver.
     * @param cantidad La cantidad a devolver.
     */
    @Transactional
    public void devolverProducto(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0) {
            throw new IllegalArgumentException("Producto no puede ser nulo y la cantidad debe ser mayor que cero.");
        }

        List<LoteProducto> lotes = loteProductoRepository.findByProductoAndEstadoInOrderByFechaAsc(
                producto, Arrays.asList(Estado.NUEVO, Estado.REACONDICIONADO)
        );

        for (LoteProducto lote : lotes) {
            if (cantidad <= 0) {
                break;
            }

            int cantidadRestanteEnLote = lote.getCantidad();
            int cantidadADevolver = Math.min(cantidad, cantidadRestanteEnLote);

            // Incrementar la cantidad en el lote actual
            lote.setCantidad(cantidadRestanteEnLote + cantidadADevolver);
            loteProductoRepository.save(lote);

            cantidad -= cantidadADevolver;
        }

        if (cantidad > 0) {
            // Si hay cantidad restante que no se pudo devolver a un lote existente,
            // podrías manejar este caso creando un nuevo lote o ajustando algún registro adicional
            throw new IllegalStateException("No se pudo devolver toda la cantidad solicitada al inventario.");
        }
    }

    /**
     * Ajusta la cantidad disponible en un lote y guarda los cambios en la base
     * de datos. Si el lote no puede cubrir la cantidad solicitada, ajusta el
     * lote a 0 y devuelve la cantidad restante.
     *
     * @param lote El lote a ajustar.
     * @param cantidadSolicitada La cantidad que se intenta despachar.
     * @return La cantidad restante que aún necesita ser despachada.
     */
    @Transactional
    private int ajustarCantidadEnLote(LoteProducto lote, int cantidadSolicitada) {
        int cantidadDisponible = lote.getCantidad();
        if (cantidadDisponible >= cantidadSolicitada) {
            lote.setCantidad(cantidadDisponible - cantidadSolicitada);
            loteProductoRepository.save(lote);
            return 0;
        } else {
            lote.setCantidad(0);
            loteProductoRepository.save(lote);
            return cantidadSolicitada - cantidadDisponible;
        }
    }

    /**
     * Encuentra un lote por su identificador.
     *
     * @param producto El producto al que pertenece el lote.
     * @param idLote El identificador del lote a buscar.
     * @return El lote encontrado, o un Optional vacío si no se encuentra.
     */
    public Optional<LoteProducto> encontrarLotePorId(Producto producto, long idLote) {
        if (producto == null) {
            return Optional.empty();
        }
        return producto.getLotes().stream().filter(lote -> lote.getId() == idLote).findFirst();
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
