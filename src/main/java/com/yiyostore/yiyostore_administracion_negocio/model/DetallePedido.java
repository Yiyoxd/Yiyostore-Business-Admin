package com.yiyostore.yiyostore_administracion_negocio.model;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Entidad que representa un detalle dentro de un pedido. Cada detalle de pedido
 * está asociado a un producto y a un lote específico de ese producto, y
 * registra la cantidad de productos vendidos.
 */
@Entity
@Table(name = "detalles_pedidos")
public class DetallePedido {

    /**
     * Identificador único del detalle de pedido. Auto-generado por la base de
     * datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * El pedido al que pertenece este detalle. Este campo establece la relación
     * muchos a uno entre DetallePedido y Pedido.
     */
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    /**
     * El producto asociado a este detalle de pedido. Este campo establece la
     * relación muchos a uno entre DetallePedido y Producto.
     */
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    /**
     * El lote específico del producto asociado a este detalle de pedido. Este
     * campo establece la relación muchos a uno entre DetallePedido y
     * LoteProducto.
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
     * @param lote El lote específico del producto.
     * @param cantidad La cantidad del producto vendida en este detalle.
     * @param precioUnitario El precio unitario del producto en el momento del
     * pedido.
     */
    public DetallePedido(Pedido pedido, Producto producto, LoteProducto lote, int cantidad, double precioUnitario) {
        this.setPedido(pedido);
        this.setProducto(producto);
        this.setLote(lote);
        this.setCantidad(cantidad);
        this.setPrecioUnitario(precioUnitario);
    }

    // Getters y Setters
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
     * Establece el pedido al que pertenece este detalle.
     *
     * @param pedido El pedido a asociar.
     */
    public void setPedido(Pedido pedido) {
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
     * Establece el producto asociado a este detalle de pedido.
     *
     * @param producto El producto a asociar.
     */
    public void setProducto(Producto producto) {
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
     * pedido.
     *
     * @param lote El lote a asociar.
     */
    public void setLote(LoteProducto lote) {
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
     * la disponible.
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
        StringBuilder sb = new StringBuilder();
        sb.append("DetallePedido{")
                .append("id=").append(id)
                .append(", pedido=").append(pedido)
                .append(", producto=").append(producto)
                .append(", lote=").append(lote)
                .append(", cantidad=").append(cantidad)
                .append(", precioUnitario=").append(precioUnitario)
                .append('}');
        return sb.toString();
    }
}
