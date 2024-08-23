package com.yiyostore.yiyostore_administracion_negocio.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yiyostore.yiyostore_administracion_negocio.model.EstadoPedido;
import com.yiyostore.yiyostore_administracion_negocio.model.enums.LugarCompra;
import com.yiyostore.yiyostore_administracion_negocio.model.enums.MetodoPago;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entidad que representa un pedido realizado por un cliente. Un pedido puede
 * contener múltiples detalles de pedido, cada uno asociado a un producto y un
 * lote específico.
 */
@Entity
@Table(name = "pedidos")
public class Pedido {

    /**
     * Identificador único del pedido, auto-generado por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Cliente que realizó el pedido.
     */
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    /**
     * Fecha en la que se realizó el pedido.
     */
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    /**
     * Lista de detalles del pedido. Cada detalle representa un producto y un
     * lote específico que se incluyó en el pedido.
     */
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetallePedido> detalles = new ArrayList<>();

    /**
     * Método de pago utilizado para este pedido.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    /**
     * Lugar donde se realizó la compra.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "lugar_compra", nullable = false)
    private LugarCompra lugarCompra;

    /**
     * Estado actual del pedido.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoPedido estado;

    /**
     * Notas adicionales sobre el pedido. Este campo permite almacenar
     * información relevante o comentarios acerca del pedido.
     */
    @Column(name = "notas", length = 500)
    private String notas;

    /**
     * Constructor por defecto necesario para JPA.
     */
    public Pedido() {
    }

    /**
     * Constructor completo para inicializar un pedido con todos sus atributos.
     *
     * @param cliente Cliente que realizó el pedido.
     * @param fecha Fecha en la que se realizó el pedido.
     * @param detalles Lista de detalles del pedido.
     * @param metodoPago Método de pago utilizado.
     * @param lugarCompra Lugar donde se realizó la compra.
     * @param notas Notas adicionales sobre el pedido.
     * @param estado Estado actual del pedido.
     */
    public Pedido(Cliente cliente, LocalDate fecha, List<DetallePedido> detalles, MetodoPago metodoPago, LugarCompra lugarCompra, String notas, EstadoPedido estado) {
        setCliente(cliente);
        setFecha(fecha);
        this.metodoPago = metodoPago;
        this.lugarCompra = lugarCompra;
        this.notas = notas;
        setEstado(estado);
        setDetalles(detalles);
    }

    /**
     * Obtiene el identificador único del pedido.
     *
     * @return ID del pedido.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del pedido.
     *
     * @param id ID del pedido.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el cliente que realizó el pedido.
     *
     * @return Cliente del pedido.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Establece el cliente que realizó el pedido y asegura que la relación
     * bidireccional se mantenga.
     *
     * @param cliente Cliente del pedido.
     */
    public void setCliente(Cliente cliente) {
        if (cliente == this.cliente || (cliente != null && cliente.equals(this.cliente))) {
            return;
        }
        if (this.cliente != null) {
            this.cliente.getPedidos().remove(this);
        }
        this.cliente = cliente;
        if (cliente != null) {
            cliente.getPedidos().add(this);
        }
    }

    /**
     * Obtiene la fecha en la que se realizó el pedido.
     *
     * @return Fecha del pedido.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha en la que se realizó el pedido.
     *
     * @param fecha Fecha del pedido.
     */
    public void setFecha(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        this.fecha = fecha;
    }

    /**
     * Obtiene la lista de detalles del pedido.
     *
     * @return Lista de detalles del pedido.
     */
    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    /**
     * Establece la lista de detalles del pedido.
     *
     * @param detalles Lista de detalles del pedido.
     */
    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles.clear();
        if (detalles != null) {
            this.detalles.addAll(detalles);
        }
    }

    /**
     * Obtiene el método de pago utilizado para el pedido.
     *
     * @return Método de pago.
     */
    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    /**
     * Establece el método de pago utilizado para el pedido.
     *
     * @param metodoPago Método de pago.
     */
    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    /**
     * Obtiene el lugar donde se realizó la compra.
     *
     * @return Lugar de compra.
     */
    public LugarCompra getLugarCompra() {
        return lugarCompra;
    }

    /**
     * Establece el lugar donde se realizó la compra.
     *
     * @param lugarCompra Lugar de compra.
     */
    public void setLugarCompra(LugarCompra lugarCompra) {
        this.lugarCompra = lugarCompra;
    }

    /**
     * Obtiene el estado actual del pedido.
     *
     * @return Estado del pedido.
     */
    public EstadoPedido getEstado() {
        return estado;
    }

    /**
     * Establece el estado del pedido.
     *
     * @param estado Estado del pedido.
     */
    public void setEstado(EstadoPedido estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }
        this.estado = estado;
    }

    /**
     * Obtiene las notas adicionales sobre el pedido.
     *
     * @return Notas adicionales del pedido.
     */
    public String getNotas() {
        return notas;
    }

    /**
     * Establece las notas adicionales sobre el pedido.
     *
     * @param notas Notas adicionales del pedido.
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * Agrega un detalle a la lista de detalles del pedido.
     *
     * @param detalle Detalle a agregar.
     */
    public void agregarDetalle(DetallePedido detalle) {
        if (detalle != null && !this.detalles.contains(detalle)) {
            detalle.setPedido(this);
            this.detalles.add(detalle);
        }
    }

    /**
     * Calcula el total del pedido sumando los subtotales de cada detalle.
     *
     * @return El total del pedido.
     */
    public double calcularTotal() {
        return this.detalles.stream()
                .mapToDouble(detalle -> detalle.getCantidad() * detalle.getPrecioUnitario())
                .sum();
    }

    /**
     * Calcula el hashcode del objeto basado en el ID del pedido.
     *
     * @return Hashcode del pedido.
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
        Pedido that = (Pedido) obj;
        return Objects.equals(id, that.id);
    }

    /**
     * Representación en cadena del objeto Pedido.
     *
     * @return Cadena que representa el objeto.
     */
    @Override
    public String toString() {
        return String.format("Pedido{id=%d, cliente=%s, fecha=%s, metodoPago=%s, lugarCompra=%s, estado=%s, detalles=%s, notas='%s'}",
                id, cliente != null ? cliente.getNombre() : "N/A", fecha, metodoPago, lugarCompra, estado, detalles, notas);
    }
}
