package com.yiyostore.yiyostore_administracion_negocio.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Representa un lote específico de un producto en el inventario.
 * Cada lote tiene su propio costo y cantidad disponible, y está asociado a un producto.
 */
@Entity
@Table(name = "lotes_productos")
public class LoteProducto {

    /**
     * Identificador único del lote.
     */
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    /**
     * El producto al que pertenece este lote.
     * Este campo establece la relación muchos a uno entre LoteProducto y Producto.
     */
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    /**
     * Costo del producto en este lote.
     * Este valor puede ser diferente entre lotes, incluso para el mismo producto.
     */
    @Column(name = "costo")
    private double costo;

    /**
     * Cantidad disponible de productos en este lote.
     * Este valor representa el inventario actual para este lote específico.
     */
    @Column(name = "cantidad_disponible")
    private int cantidadDisponible;

    /**
     * Constructor vacío. Es necesario para JPA.
     */
    public LoteProducto() {
    }

    /**
     * Constructor para inicializar un lote de producto con todos sus atributos.
     *
     * @param id Identificador único del lote.
     * @param costo Costo del producto en este lote.
     * @param cantidadDisponible Cantidad disponible de productos en este lote.
     * @param producto El producto al que pertenece este lote.
     */
    public LoteProducto(long id, double costo, int cantidadDisponible, Producto producto) {
        this.id = id;
        this.costo = costo;
        this.cantidadDisponible = cantidadDisponible;
        this.producto = producto;
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
     * Obtiene el producto asociado a este lote.
     *
     * @return El producto asociado.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto asociado a este lote.
     *
     * @param producto El nuevo producto asociado.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
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
     * @throws IllegalArgumentException si el costo es negativo.
     */
    public void setCosto(double costo) {
        if (costo < 0) {
            throw new IllegalArgumentException("El costo no puede ser negativo.");
        }
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
     * @throws IllegalArgumentException si la cantidad disponible es negativa.
     */
    public void setCantidadDisponible(int cantidadDisponible) {
        if (cantidadDisponible < 0) {
            throw new IllegalArgumentException("La cantidad disponible no puede ser negativa.");
        }
        this.cantidadDisponible = cantidadDisponible;
    }

    /**
     * Devuelve una representación en forma de cadena del lote de producto.
     * Incluye el identificador, el costo, la cantidad disponible, y el nombre del producto asociado.
     *
     * @return Una cadena que representa el lote de producto.
     */
    @Override
    public String toString() {
        return "LoteProducto{"
                + "id=" + id
                + ", producto=" + (producto != null ? producto.getNombre() : "null")
                + ", costo=" + costo
                + ", cantidadDisponible=" + cantidadDisponible
                + '}';
    }

    /**
     * Compara este objeto LoteProducto con otro para verificar si son iguales.
     * Dos lotes son considerados iguales si tienen el mismo identificador.
     *
     * @param o El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoteProducto that = (LoteProducto) o;
        return id == that.id;
    }

    /**
     * Calcula el código hash de este objeto LoteProducto.
     *
     * @return El código hash del lote de producto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}