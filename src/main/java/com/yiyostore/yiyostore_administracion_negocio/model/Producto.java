 package com.yiyostore.yiyostore_administracion_negocio.model;

import java.time.LocalDate;

/**
 * Representa un producto en el inventario. Incluye detalles como nombre, descripción, precio, 
 * costo, categoría, estado, fecha de adición y link de compra.
 */
public class Producto {
    private long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private double costo;
    private Categoria categoria;
    private Estado estado;
    private LocalDate fechaDeAdicion;
    private String linkDeCompra;

    /**
     * Constructor vacío.
     */
    public Producto() {
    }

    /**
     * Constructor para inicializar un producto con todos sus atributos.
     *
     * @param id             Identificador único del producto.
     * @param nombre         Nombre del producto.
     * @param descripcion    Descripción detallada del producto.
     * @param precio         Precio del producto.
     * @param costo          Costo del producto.
     * @param categoria      Categoría del producto.
     * @param estado         Estado del producto (nuevo, usado, reacondicionado, defectuoso).
     * @param fechaDeAdicion Fecha en que el producto fue añadido al inventario.
     * @param linkDeCompra   Link de compra del producto.
     */
    public Producto(long id, String nombre, String descripcion, double precio, double costo, Categoria categoria, Estado estado, LocalDate fechaDeAdicion, String linkDeCompra) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.costo = costo;
        this.categoria = categoria;
        this.estado = estado;
        this.fechaDeAdicion = fechaDeAdicion;
        this.linkDeCompra = linkDeCompra;
    }

    // Getters y Setters

    /**
     * Obtiene el identificador único del producto.
     *
     * @return El identificador del producto.
     */
    public long getId() {
        return id;
    }

    /**
     * Establece el identificador único del producto.
     *
     * @param id El nuevo identificador del producto.
     */
    public void setId(long id) {
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
     * Obtiene el precio del producto.
     *
     * @return El precio del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     *
     * @param precio El nuevo precio del producto.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el costo del producto.
     *
     * @return El costo del producto.
     */
    public double getCosto() {
        return costo;
    }

    /**
     * Establece el costo del producto.
     *
     * @param costo El nuevo costo del producto.
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }

    /**
     * Obtiene la categoría del producto.
     *
     * @return La categoría del producto.
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del producto.
     *
     * @param categoria La nueva categoría del producto.
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene el estado del producto.
     *
     * @return El estado del producto.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Establece el estado del producto.
     *
     * @param estado El nuevo estado del producto.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la fecha en que el producto fue añadido al inventario.
     *
     * @return La fecha de adición del producto.
     */
    public LocalDate getFechaDeAdicion() {
        return fechaDeAdicion;
    }

    /**
     * Establece la fecha en que el producto fue añadido al inventario.
     *
     * @param fechaDeAdicion La nueva fecha de adición del producto.
     */
    public void setFechaDeAdicion(LocalDate fechaDeAdicion) {
        this.fechaDeAdicion = fechaDeAdicion;
    }

    /**
     * Obtiene el link de compra del producto.
     *
     * @return El link de compra del producto.
     */
    public String getLinkDeCompra() {
        return linkDeCompra;
    }

    /**
     * Establece el link de compra del producto.
     *
     * @param linkDeCompra El nuevo link de compra del producto.
     */
    public void setLinkDeCompra(String linkDeCompra) {
        this.linkDeCompra = linkDeCompra;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto {")
          .append("\n  ID=").append(id)
          .append(",\n  Nombre='").append(nombre).append('\'')
          .append(",\n  Descripción='").append(descripcion).append('\'')
          .append(",\n  Precio=").append(precio)
          .append(",\n  Costo=").append(costo)
          .append(",\n  Categoría=").append(categoria)
          .append(",\n  Estado=").append(estado)
          .append(",\n  Fecha de Adición=").append(fechaDeAdicion)
          .append(",\n  Link de Compra='").append(linkDeCompra).append('\'')
          .append("\n}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producto producto = (Producto) o;

        return id == producto.id;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        return result;
    }
}