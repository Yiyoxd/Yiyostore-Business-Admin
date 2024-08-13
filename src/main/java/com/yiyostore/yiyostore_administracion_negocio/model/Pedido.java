package com.yiyostore.yiyostore_administracion_negocio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Representa un pedido realizado por un cliente. Incluye información sobre el
 * cliente, los productos pedidos, la fecha del pedido, la fecha de entrega, el
 * estado del pedido, el método de pago, el lugar de compra y el total del
 * pedido.
 */
@Entity
@Table(name = "pedidos")
public class Pedido {

    /**
     * Identificador único del pedido.
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * El cliente que realizó el pedido.
     */
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    /**
     * Lista de productos que forman parte del pedido.
     */
    @OneToMany(mappedBy = "pedido")
    private List<ProductoPedido> productos;

    /**
     * Fecha en la que se realizó el pedido.
     */
    @Column(name = "fecha_pedido")
    private LocalDate fechaPedido;

    /**
     * Fecha prevista para la entrega del pedido.
     */
    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    /**
     * Estado actual del pedido.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    /**
     * Método de pago utilizado para este pedido.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago")
    private MetodoPago metodoPago;

    /**
     * Lugar donde se realizó la compra del pedido.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "lugar_compra")
    private LugarCompra lugarCompra;

    /**
     * Total del pedido.
     */
    @Column(name = "total")
    private double total;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Pedido() {
    }

    /**
     * Constructor para inicializar un pedido con todos sus atributos.
     *
     * @param cliente El cliente que realizó el pedido.
     * @param productos La lista de productos pedidos.
     * @param fechaPedido La fecha en que se realizó el pedido.
     * @param fechaEntrega La fecha en que se entregará el pedido.
     * @param estado El estado del pedido.
     * @param metodoPago El método de pago utilizado.
     * @param lugarCompra El lugar donde se realizó la compra.
     * @param total El total del pedido.
     */
    public Pedido(Cliente cliente, List<ProductoPedido> productos, LocalDate fechaPedido, LocalDate fechaEntrega, Estado estado, MetodoPago metodoPago, LugarCompra lugarCompra, double total) {
        this.cliente = cliente;
        this.productos = productos;
        this.fechaPedido = fechaPedido;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.lugarCompra = lugarCompra;
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
     * Obtiene el estado actual del pedido.
     *
     * @return El estado del pedido.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual del pedido.
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
     * Obtiene el lugar donde se realizó la compra del pedido.
     *
     * @return El lugar de compra del pedido.
     */
    public LugarCompra getLugarCompra() {
        return lugarCompra;
    }

    /**
     * Establece el lugar donde se realizó la compra del pedido.
     *
     * @param lugarCompra El nuevo lugar de compra del pedido.
     */
    public void setLugarCompra(LugarCompra lugarCompra) {
        this.lugarCompra = lugarCompra;
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

    /**
     * Devuelve una representación en forma de cadena del pedido.
     *
     * @return Una cadena que representa el pedido.
     */
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
                .append(",\n  Lugar de Compra=").append(lugarCompra)
                .append(",\n  Total=").append(total)
                .append("\n}");
        return sb.toString();
    }

    /**
     * Compara este objeto Pedido con otro para verificar si son iguales.
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
        Pedido pedido = (Pedido) o;
        return id.equals(pedido.id);
    }

    /**
     * Calcula el código hash de este objeto Pedido.
     *
     * @return El código hash del pedido.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, total);
    }
}
