package com.yiyostore.yiyostore_administracion_negocio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidad que representa un lote específico de un producto en el inventario.
 * Cada lote tiene su propio costo y cantidad disponible, y está asociado a un
 * producto. El campo adicional `notas` se utiliza para registrar cualquier
 * comentario o información relevante sobre el estado del lote.
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
    @JsonBackReference
    private Producto producto;

    /**
     * Costo del producto en este lote. Este valor puede ser diferente entre
     * lotes, incluso para el mismo producto.
     */
    @Column(name = "costo", nullable = false)
    private double costo;

    /**
     * Cantidad disponible de productos en este lote. Este valor representa el
     * inventario actual para este lote específico.
     */
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    /**
     * Link de compra para el lote. Este valor es la URL desde donde se puede
     * adquirir el lote.
     */
    @Column(name = "link_de_compra", length = 255)
    private String linkDeCompra;

    /**
     * Notas adicionales sobre el lote. Este campo se usa para registrar
     * cualquier información relevante sobre el estado del lote.
     */
    @Column(name = "notas", length = 255)
    private String notas;

    /**
     * Estado del producto en este lote (nuevo, usado, etc.).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    /**
     * Fecha en la que se adquirió el lote.
     */
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    /**
     * Constructor vacío requerido por JPA.
     */
    public LoteProducto() {
    }

    /**
     * Constructor completo para inicializar un lote de producto con todos sus
     * atributos, incluyendo las notas, el estado y la fecha de adquisición.
     *
     * @param costo Costo del producto en este lote.
     * @param cantidad Cantidad disponible de productos en este lote.
     * @param producto El producto al que pertenece este lote.
     * @param linkDeCompra Link de compra del lote.
     * @param notas Notas adicionales sobre el estado del lote.
     * @param estado Estado del producto en este lote.
     * @param fecha Fecha en la que se adquirió el lote.
     */
    public LoteProducto(Producto producto, double costo, int cantidad, String linkDeCompra, String notas, Estado estado, LocalDate fecha) {
        this.producto = producto;
        this.setCosto(costo);
        this.setCantidad(cantidad);
        this.linkDeCompra = linkDeCompra;
        this.notas = notas;
        this.estado = estado;
        this.fecha = fecha;
    }

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
     * Obtiene el costo del producto en este lote.
     *
     * @return Costo del producto.
     */
    public double getCosto() {
        return costo;
    }

    /**
     * Establece el costo del producto en este lote. El costo no puede ser
     * negativo.
     *
     * @param costo Costo del producto.
     * @throws IllegalArgumentException si el costo es negativo.
     */
    public void setCosto(double costo) {
        if (costo < 0) {
            throw new IllegalArgumentException("El costo no puede ser negativo");
        }
        this.costo = costo;
    }

    /**
     * Obtiene la cantidad disponible de productos en este lote.
     *
     * @return Cantidad disponible.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad disponible de productos en este lote. La cantidad
     * no puede ser negativa.
     *
     * @param cantidad Cantidad disponible.
     * @throws IllegalArgumentException si la cantidad es negativa.
     */
    public void setCantidad(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        this.cantidad = cantidad;
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
     * Obtiene las notas adicionales sobre el lote.
     *
     * @return Notas del lote.
     */
    public String getNotas() {
        return notas;
    }

    /**
     * Establece las notas adicionales sobre el lote.
     *
     * @param notas Notas del lote.
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * Obtiene el estado del producto en este lote.
     *
     * @return El estado del producto.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Establece el estado del producto en este lote.
     *
     * @param estado El nuevo estado del producto.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la fecha en la que se adquirió el lote.
     *
     * @return Fecha de adquisición del lote.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha en la que se adquirió el lote.
     *
     * @param fecha Fecha de adquisición del lote.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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
                .append(", cantidadDisponible=").append(cantidad)
                .append(", linkDeCompra='").append(linkDeCompra).append('\'')
                .append(", notas='").append(notas).append('\'')
                .append(", estado=").append(estado)
                .append(", fecha=").append(fecha)
                .append('}');
        return sb.toString();
    }
}
