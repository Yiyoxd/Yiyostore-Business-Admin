package com.yiyostore.yiyostore_administracion_negocio.model;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
 
/**
 * Representa un pedido realizado por un cliente. Incluye información sobre el cliente, 
 * los productos pedidos, la fecha del pedido, la fecha de entrega, el estado del pedido,
 * el método de pago y el total del pedido.
 */
public class Pedido {
    private Long id;
    private Cliente cliente;
    private List<ProductoPedido> productos;
    private LocalDate fechaPedido;
    private LocalDate fechaEntrega;
    private Estado estado;
    private MetodoPago metodoPago;
    private double total;

    /**
     * Constructor vacío.
     */
    public Pedido() {
    }

    /**
     * Constructor para inicializar un pedido con todos sus atributos.
     *
     * @param cliente       El cliente que realizó el pedido.
     * @param productos     La lista de productos pedidos.
     * @param fechaPedido   La fecha en que se realizó el pedido.
     * @param fechaEntrega  La fecha en que se entregará el pedido.
     * @param estado        El estado del pedido.
     * @param metodoPago    El método de pago utilizado.
     * @param total         El total del pedido.
     */
    public Pedido(Cliente cliente, List<ProductoPedido> productos, LocalDate fechaPedido, LocalDate fechaEntrega, Estado estado, MetodoPago metodoPago, double total) {
        this.cliente = cliente;
        this.productos = productos;
        this.fechaPedido = fechaPedido;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.total = total;
    }

    // Getters y Setters

    /**
     * Obtiene el identificador único del pedido.
     *
     * @return El identificador del pedido.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del pedido.
     *
     * @param id El nuevo identificador del pedido.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el cliente que realizó el pedido.
     *
     * @return El cliente del pedido.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Establece el cliente que realizó el pedido.
     *
     * @param cliente El nuevo cliente del pedido.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Obtiene la lista de productos pedidos.
     *
     * @return La lista de productos pedidos.
     */
    public List<ProductoPedido> getProductos() {
        return productos;
    }

    /**
     * Establece la lista de productos pedidos.
     *
     * @param productos La nueva lista de productos pedidos.
     */
    public void setProductos(List<ProductoPedido> productos) {
        this.productos = productos;
    }

    /**
     * Obtiene la fecha en que se realizó el pedido.
     *
     * @return La fecha del pedido.
     */
    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    /**
     * Establece la fecha en que se realizó el pedido.
     *
     * @param fechaPedido La nueva fecha del pedido.
     */
    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    /**
     * Obtiene la fecha en que se entregará el pedido.
     *
     * @return La fecha de entrega del pedido.
     */
    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * Establece la fecha en que se entregará el pedido.
     *
     * @param fechaEntrega La nueva fecha de entrega del pedido.
     */
    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    /**
     * Obtiene el estado del pedido.
     *
     * @return El estado del pedido.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Establece el estado del pedido.
     *
     * @param estado El nuevo estado del pedido.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el método de pago utilizado para el pedido.
     *
     * @return El método de pago del pedido.
     */
    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    /**
     * Establece el método de pago utilizado para el pedido.
     *
     * @param metodoPago El nuevo método de pago del pedido.
     */
    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    /**
     * Obtiene el total del pedido.
     *
     * @return El total del pedido.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Establece el total del pedido.
     *
     * @param total El nuevo total del pedido.
     */
    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido {")
          .append("\n  ID=").append(id)
          .append(",\n  Cliente=").append(cliente)
          .append(",\n  Productos=").append(productos)
          .append(",\n  Fecha de Pedido=").append(fechaPedido)
          .append(",\n  Fecha de Entrega=").append(fechaEntrega)
          .append(",\n  Estado=").append(estado)
          .append(",\n  Método de Pago=").append(metodoPago)
          .append(",\n  Total=").append(total)
          .append("\n}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        
        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, total);
    }
}
