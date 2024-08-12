package com.yiyostore.yiyostore_administracion_negocio.model;


/**
 * Representa un lote específico de un producto en el inventario. 
 * Cada lote tiene su propio costo y cantidad disponible.
 */
public class LoteProducto {
    private long id;
    private double costo;
    private int cantidadDisponible;

    /**
     * Constructor vacío.
     */
    public LoteProducto() {
    }

    /**
     * Constructor para inicializar un lote de producto con todos sus atributos.
     *
     * @param id                 Identificador único del lote.
     * @param costo              Costo del producto en este lote.
     * @param cantidadDisponible Cantidad disponible de productos en este lote.
     */
    public LoteProducto(long id, double costo, int cantidadDisponible) {
        this.id = id;
        this.costo = costo;
        this.cantidadDisponible = cantidadDisponible;
    }

    /**
     * Obtiene el identificador único del lote.
     *
     * @return El identificador del lote.
     */
    public long getId() {
        return id;
    }

    /**
     * Establece el identificador único del lote.
     *
     * @param id El nuevo identificador del lote.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obtiene el costo del producto en este lote.
     *
     * @return El costo del producto en este lote.
     */
    public double getCosto() {
        return costo;
    }

    /**
     * Establece el costo del producto en este lote.
     *
     * @param costo El nuevo costo del producto en este lote.
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }

    /**
     * Obtiene la cantidad disponible de productos en este lote.
     *
     * @return La cantidad disponible de productos en este lote.
     */
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    /**
     * Establece la cantidad disponible de productos en este lote.
     *
     * @param cantidadDisponible La nueva cantidad disponible de productos en este lote.
     */
    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
}
