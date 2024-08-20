package com.yiyostore.yiyostore_administracion_negocio.model;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Entidad que representa un detalle dentro de un pedido. Cada detalle de pedido
 * está asociado a un producto y a un lote específico de ese producto, y
 * registra la cantidad de productos vendidos junto con su precio unitario en el
 * momento del pedido.
 */
@Entity
@Table(name = "detalles_pedidos")
public class DetallePedido {

    /**
     * Identificador único del detalle de pedido, auto-generado por la base de
     * datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * El pedido al que pertenece este detalle. Establece la relación muchos a
     * uno entre DetallePedido y Pedido.
     */
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    /**
     * El producto asociado a este detalle de pedido. Establece la relación
     * muchos a uno entre DetallePedido y Producto.
     */
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    /**
     * El lote específico del producto asociado a este detalle de pedido.
     * Establece la relación muchos a uno entre DetallePedido y LoteProducto.
     */
    @ManyToOne
    @JoinColumn(name = "lote_id", nullable = false)
    private LoteProducto lote;

    /**
     * La cantidad del producto, proveniente del lote específico, vendida en
     * este detalle de pedido.
     */
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    /**
     * Precio unitario del producto en el momento del pedido.
     */
    @Column(name = "precio_unitario", nullable = false)
    private double precioUnitario;

    /**
     * Constructor vacío requerido por JPA.
     */
    public DetallePedido() {
    }

    /**
     * Constructor completo para inicializar un detalle de pedido con todos sus
     * atributos.
     *
     * @param pedido El pedido al que pertenece este detalle.
     * @param producto El producto asociado a este detalle.
     * @param lote El lote específico del producto asociado.
     * @param cantidad La cantidad del producto vendida en este detalle.
     * @param precioUnitario El precio unitario del producto en el momento del
     * pedido.
     */
    public DetallePedido(Pedido pedido, Producto producto, LoteProducto lote, int cantidad, double precioUnitario) {
        setPedido(pedido);
        setProducto(producto);
        setLote(lote);
        setCantidad(cantidad);
        setPrecioUnitario(precioUnitario);
    }

    /**
     * Obtiene el identificador único del detalle de pedido.
     *
     * @return ID del detalle de pedido.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del detalle de pedido.
     *
     * @param id ID del detalle de pedido.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el pedido al que pertenece este detalle.
     *
     * @return El pedido asociado.
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Establece el pedido al que pertenece este detalle y asegura que la
     * relación bidireccional se mantenga. Si este detalle ya estaba asociado a
     * otro pedido, se elimina de la lista de detalles de ese pedido.
     *
     * @param pedido El pedido a asociar.
     */
    public void setPedido(Pedido pedido) {
        if (this.pedido != null) {
            this.pedido.getDetalles().remove(this);
        }
        this.pedido = pedido;
        if (pedido != null && !pedido.getDetalles().contains(this)) {
            pedido.getDetalles().add(this);
        }
    }

    /**
     * Obtiene el producto asociado a este detalle de pedido.
     *
     * @return El producto asociado.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto asociado a este detalle de pedido. El producto no
     * puede ser nulo.
     *
     * @param producto El producto a asociar.
     * @throws IllegalArgumentException si el producto es nulo.
     */
    public void setProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        this.producto = producto;
    }

    /**
     * Obtiene el lote específico del producto asociado a este detalle de
     * pedido.
     *
     * @return El lote asociado.
     */
    public LoteProducto getLote() {
        return lote;
    }

    /**
     * Establece el lote específico del producto asociado a este detalle de
     * pedido. El lote no puede ser nulo.
     *
     * @param lote El lote a asociar.
     * @throws IllegalArgumentException si el lote es nulo.
     */
    public void setLote(LoteProducto lote) {
        if (lote == null) {
            throw new IllegalArgumentException("El lote no puede ser nulo");
        }
        this.lote = lote;
    }

    /**
     * Obtiene la cantidad del producto vendida en este detalle de pedido.
     *
     * @return La cantidad vendida.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del producto vendida en este detalle de pedido. La
     * cantidad no puede ser negativa ni mayor que la cantidad disponible en el
     * lote.
     *
     * @param cantidad La cantidad vendida.
     * @throws IllegalArgumentException si la cantidad es negativa o mayor que
     * la disponible en el lote.
     */
    public void setCantidad(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        if (lote != null && cantidad > lote.getCantidad()) {
            throw new IllegalArgumentException("La cantidad no puede ser mayor que la cantidad disponible en el lote");
        }
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio unitario del producto en el momento del pedido.
     *
     * @return El precio unitario.
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Establece el precio unitario del producto en el momento del pedido. El
     * precio no puede ser negativo.
     *
     * @param precioUnitario El precio unitario a establecer.
     * @throws IllegalArgumentException si el precio es negativo.
     */
    public void setPrecioUnitario(double precioUnitario) {
        if (precioUnitario < 0) {
            throw new IllegalArgumentException("El precio unitario no puede ser negativo");
        }
        this.precioUnitario = precioUnitario;
    }

    /**
     * Calcula el hashcode del objeto basado en el ID del detalle de pedido.
     *
     * @return Hashcode del detalle de pedido.
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
        DetallePedido that = (DetallePedido) obj;
        return Objects.equals(id, that.id);
    }

    /**
     * Representación en cadena del objeto DetallePedido.
     *
     * @return Cadena que representa el objeto.
     */
    @Override
    public String toString() {
        return String.format("DetallePedido{id=%d, pedido=%s, producto=%s, lote=%s, cantidad=%d, precioUnitario=%.2f}",
                id, pedido != null ? pedido.getId() : "N/A", producto != null ? producto.getId() : "N/A",
                lote != null ? lote.getId() : "N/A", cantidad, precioUnitario);
    }
}
