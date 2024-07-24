package com.yiyostore.yiyostore_administracion_negocio.model;

import java.util.Objects;

/**
 * Representa un producto dentro de un pedido. Incluye información sobre el producto, 
 * la cantidad pedida y el precio del producto en el pedido.
 */
public class ProductoPedido {
    private Long id;
    private Producto producto;
    private Pedido pedido;
    private int cantidad;
    private double precio;

    /**
     * Constructor vacío.
     */
    public ProductoPedido() {
    }

    /**
     * Constructor para inicializar un producto dentro de un pedido con todos sus atributos.
     *
     * @param producto El producto incluido en el pedido.
     * @param pedido   El pedido al que pertenece este producto.
     * @param cantidad La cantidad de este producto en el pedido.
     * @param precio   El precio unitario de este producto en el pedido.
     */
    public ProductoPedido(Producto producto, Pedido pedido, int cantidad, double precio) {
        this.producto = producto;
        this.pedido = pedido;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Getters y Setters

    /**
     * Obtiene el identificador único de la relación producto-pedido.
     *
     * @return El identificador de la relación producto-pedido.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la relación producto-pedido.
     *
     * @param id El nuevo identificador de la relación producto-pedido.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el producto incluido en el pedido.
     *
     * @return El producto en el pedido.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto incluido en el pedido.
     *
     * @param producto El nuevo producto en el pedido.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Obtiene el pedido al que pertenece este producto.
     *
     * @return El pedido al que pertenece este producto.
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Establece el pedido al que pertenece este producto.
     *
     * @param pedido El nuevo pedido al que pertenece este producto.
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Obtiene la cantidad de este producto en el pedido.
     *
     * @return La cantidad de este producto en el pedido.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de este producto en el pedido.
     *
     * @param cantidad La nueva cantidad de este producto en el pedido.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio unitario de este producto en el pedido.
     *
     * @return El precio unitario de este producto en el pedido.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio unitario de este producto en el pedido.
     *
     * @param precio El nuevo precio unitario de este producto en el pedido.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProductoPedido {")
          .append("\n  ID=").append(id)
          .append(",\n  Producto=").append(producto)
          .append(",\n  Pedido=").append(pedido)
          .append(",\n  Cantidad=").append(cantidad)
          .append(",\n  Precio=").append(precio)
          .append("\n}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoPedido that = (ProductoPedido) o;
        return cantidad == that.cantidad &&
            Double.compare(that.precio, precio) == 0 &&
            Objects.equals(id, that.id) &&
            Objects.equals(producto, that.producto) &&
            Objects.equals(pedido, that.pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, producto, pedido, cantidad, precio);
    }
}