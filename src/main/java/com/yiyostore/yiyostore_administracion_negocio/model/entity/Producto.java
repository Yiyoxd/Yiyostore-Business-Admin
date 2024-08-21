package com.yiyostore.yiyostore_administracion_negocio.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un producto en el inventario. Cada producto puede estar asociado a
 * múltiples lotes.
 */
@Entity
@Table(name = "productos")
public class Producto {

    /**
     * Identificador único del producto. Generado automáticamente por la base de
     * datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Nombre del producto.
     */
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /**
     * Descripción detallada del producto.
     */
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    /**
     * Precio de venta del producto. Debe ser un valor positivo.
     */
    @Column(name = "precio", nullable = false)
    private double precio;

    /**
     * Lista de lotes asociados al producto. Cada lote está relacionado con un
     * producto a través de la relación bidireccional.
     */
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<LoteProducto> lotes = new ArrayList<>();

    /**
     * Fecha en que el producto fue añadido al inventario.
     */
    @Column(name = "fecha_adicion")
    private LocalDate fechaAdicion;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Producto() {
    }

    /**
     * Constructor para inicializar un producto con todos sus atributos.
     *
     * @param nombre Nombre del producto.
     * @param descripcion Descripción detallada del producto.
     * @param precio Precio de venta del producto.
     * @param fechaAdicion Fecha en que el producto fue añadido al inventario.
     */
    public Producto(String nombre, String descripcion, double precio, LocalDate fechaAdicion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.setPrecio(precio);
        this.fechaAdicion = fechaAdicion != null ? fechaAdicion : LocalDate.now();
    }

    /**
     * Obtiene el identificador único del producto.
     *
     * @return El identificador del producto.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del producto.
     *
     * @param id El nuevo identificador del producto.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre El nuevo nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción detallada del producto.
     *
     * @return La descripción del producto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción detallada del producto.
     *
     * @param descripcion La nueva descripción del producto.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio de venta del producto.
     *
     * @return El precio de venta del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de venta del producto. El precio no puede ser
     * negativo.
     *
     * @param precio El nuevo precio de venta del producto.
     * @throws IllegalArgumentException si el precio es negativo.
     */
    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    /**
     * Obtiene la lista de lotes asociados al producto.
     *
     * @return La lista de lotes asociados al producto.
     */
    public List<LoteProducto> getLotes() {
        return lotes;
    }

    /**
     * Obtiene la fecha en que el producto fue añadido al inventario.
     *
     * @return La fecha de adición del producto.
     */
    public LocalDate getFechaDeAdicion() {
        return fechaAdicion;
    }

    /**
     * Establece la fecha en que el producto fue añadido al inventario.
     *
     * @param fechaDeAdicion La nueva fecha de adición del producto.
     */
    public void setFechaDeAdicion(LocalDate fechaDeAdicion) {
        this.fechaAdicion = fechaDeAdicion;
    }

    /**
     * Agrega un lote a la lista de lotes del producto.
     *
     * @param lote El lote a agregar.
     * @throws IllegalArgumentException si el lote ya pertenece a otro producto.
     */
    public void agregarLote(LoteProducto lote) {
        if (lote == null) {
            throw new IllegalArgumentException("El lote no puede ser nulo");
        }
        
        if (!this.lotes.contains(lote)) {
            lote.setProducto(this);
            this.lotes.add(lote);
        }
    }

    /**
     * Compara este producto con otro objeto para verificar si son iguales.
     *
     * @param o El objeto a comparar.
     * @return true si los objetos son iguales (misma id), false en caso
     * contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Producto producto = (Producto) o;
        return id != null ? id.equals(producto.id) : producto.id == null;
    }

    /**
     * Genera un código hash para este producto basado en su identificador.
     *
     * @return El código hash generado.
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * Devuelve una representación en forma de cadena de este producto.
     *
     * @return Una cadena que representa el producto.
     */
    @Override
    public String toString() {
        return String.format("Producto {\n  id=%d,\n  nombre='%s',\n  descripcion='%s',\n  precio=%.2f,\n  lotes=%s,\n  fechaDeAdicion=%s\n}",
                id, nombre, descripcion, precio, lotes, fechaAdicion);
    }

}
