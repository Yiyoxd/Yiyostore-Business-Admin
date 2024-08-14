package com.yiyostore.yiyostore_administracion_negocio.model;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Entidad que representa un lote específico de un producto en el inventario.
 * Cada lote tiene su propio costo y cantidad disponible, y está asociado a un
 * producto.
 */
@Entity
@Table(name = "lotes_productos")
public class LoteProducto {

    /**
     * Identificador único del lote. Auto-generado por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * El producto al que pertenece este lote. Este campo establece la relación
     * muchos a uno entre LoteProducto y Producto.
     */
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    /**
     * Costo del producto en este lote. Este valor puede ser diferente entre
     * lotes, incluso para el mismo producto.
     */
    @Column(name = "costo")
    private double costo;

    /**
     * Cantidad disponible de productos en este lote. Este valor representa el
     * inventario actual para este lote específico.
     */
    @Column(name = "cantidad_disponible")
    private int cantidadDisponible;

    /**
     * Link de compra para el lote. Este valor es la URL desde donde se puede
     * adquirir el lote.
     */
    @Column(name = "link_de_compra")
    private String linkDeCompra;

    /**
     * Constructor vacío requerido por JPA.
     */
    public LoteProducto() {
    }

    /**
     * Constructor completo para inicializar un lote de producto con todos sus
     * atributos.
     *
     * @param id Identificador único del lote.
     * @param costo Costo del producto en este lote.
     * @param cantidadDisponible Cantidad disponible de productos en este lote.
     * @param producto El producto al que pertenece este lote.
     * @param linkDeCompra Link de compra del lote.
     */
    public LoteProducto(Long id, double costo, int cantidadDisponible, Producto producto, String linkDeCompra) {
        this.id = id;
        this.costo = costo;
        this.cantidadDisponible = cantidadDisponible;
        this.producto = producto;
        this.linkDeCompra = linkDeCompra;
    }

    // Getters y Setters
    /**
     * Obtiene el identificador único del lote.
     *
     * @return ID del lote.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del lote.
     *
     * @param id ID del lote.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el producto al que pertenece este lote.
     *
     * @return Producto asociado al lote.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto al que pertenece este lote.
     *
     * @param producto Producto al que se asociará el lote.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Obtiene el costo del producto en este lote.
     *
     * @return Costo del producto.
     */
    public double getCosto() {
        return costo;
    }

    /**
     * Establece el costo del producto en este lote.
     *
     * @param costo Costo del producto.
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }

    /**
     * Obtiene la cantidad disponible de productos en este lote.
     *
     * @return Cantidad disponible.
     */
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    /**
     * Establece la cantidad disponible de productos en este lote.
     *
     * @param cantidadDisponible Cantidad disponible.
     */
    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    /**
     * Obtiene el link de compra para el lote.
     *
     * @return Link de compra del lote.
     */
    public String getLinkDeCompra() {
        return linkDeCompra;
    }

    /**
     * Establece el link de compra para el lote.
     *
     * @param linkDeCompra Link de compra del lote.
     */
    public void setLinkDeCompra(String linkDeCompra) {
        this.linkDeCompra = linkDeCompra;
    }

    /**
     * Calcula el hashcode del objeto basado en el ID del lote.
     *
     * @return Hashcode del lote.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Compara este objeto con otro para determinar si son iguales.
     *
     * @param obj Objeto con el cual comparar.
     * @return true si los objetos son iguales (misma id), false en caso
     * contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LoteProducto that = (LoteProducto) obj;
        return Objects.equals(id, that.id);
    }

    /**
     * Representación en cadena del objeto LoteProducto.
     *
     * @return Cadena que representa el objeto.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LoteProducto{")
                .append("id=").append(id)
                .append(", producto=").append(producto)
                .append(", costo=").append(costo)
                .append(", cantidadDisponible=").append(cantidadDisponible)
                .append(", linkDeCompra='").append(linkDeCompra).append('\'')
                .append('}');
        return sb.toString();
    }
}
