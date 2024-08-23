package com.yiyostore.yiyostore_administracion_negocio.controller;

import com.yiyostore.yiyostore_administracion_negocio.model.dto.ProductoInventarioDTO;
import com.yiyostore.yiyostore_administracion_negocio.service.InventarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para gestionar operaciones relacionadas con el inventario.
 * Este controlador expone endpoints que permiten interactuar con el inventario
 * de la aplicación.
 */
@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    /**
     * Constructor del controlador que inyecta el servicio de inventario.
     *
     * @param inventarioService El servicio de inventario utilizado para
     * realizar operaciones sobre el inventario.
     */
    @Autowired
    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    /**
     * Endpoint para calcular el costo total del inventario.
     *
     * Este método maneja solicitudes HTTP GET en la ruta
     * "/api/inventario/costo-total". Llama al servicio para calcular el costo
     * total de todos los productos en el inventario y devuelve el resultado al
     * cliente.
     *
     * @return Un {@link ResponseEntity} que contiene el costo total del
     * inventario como un valor de tipo {@link double}, junto con un estado HTTP
     * 200 OK.
     */
    @GetMapping("/costo-total")
    public ResponseEntity<Double> calcularCostoTotalInventario() {
        double costoTotal = inventarioService.calcularCostoTotalInventario();
        return ResponseEntity.ok(costoTotal);
    }
    
    /**
     * Endpoint para calcular el precio total del inventario.
     *
     * Este método maneja solicitudes HTTP GET en la ruta
     * "/api/inventario/costo-total". Llama al servicio para calcular el costo
     * total de todos los productos en el inventario y devuelve el resultado al
     * cliente.
     *
     * @return Un {@link ResponseEntity} que contiene el precio total del
     * inventario como un valor de tipo {@link double}, junto con un estado HTTP
     * 200 OK.
     */
    @GetMapping("/precio-total")
    public ResponseEntity<Double> calcularPrecioVentaTotalInventario() {
        double costoTotal = inventarioService.calcularPrecioVentaTotalInventario();
        return ResponseEntity.ok(costoTotal);
    }
    
    @GetMapping("/resumen-inventario")
    public ResponseEntity<List<ProductoInventarioDTO>> resumenInventario(){
        List<ProductoInventarioDTO> resumen = inventarioService.obtenerResumenInventario();
        return ResponseEntity.ok(resumen);
    }
}
