package com.yiyostore.yiyostore_administracion_negocio.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Representa un producto en el inventario. Cada producto puede estar asociado a
 * múltiples lotes, donde cada lote tiene un costo y cantidad disponible
 * específicos.
 */
public class Producto {

    private long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private List<LoteProducto> lotes; 
    private Categoria categoria;
    private Estado estado;
    private LocalDate fechaDeAdicion;

    /**
     * Constructor vacío.
     */
    public Producto() {
        this.lotes = new ArrayList<>();
    }

    /**
     * Constructor para inicializar un producto con todos sus atributos.
     *
     * @param id Identificador único del producto.
     * @param nombre Nombre del producto.
     * @param descripcion Descripción detallada del producto.
     * @param precio Precio de venta del producto.
     * @param lotes Lista de lotes del producto.
     * @param categoria Categoría del producto.
     * @param estado Estado del producto (nuevo, usado, etc.).
     * @param fechaDeAdicion Fecha en que el producto fue añadido al inventario.
     */
    public Producto(long id, String nombre, String descripcion, double precio, List<LoteProducto> lotes, Categoria categoria, Estado estado, LocalDate fechaDeAdicion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.lotes = lotes != null ? lotes : new ArrayList<>();
        this.categoria = categoria;
        this.estado = estado;
        this.fechaDeAdicion = fechaDeAdicion;
    }

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
     * Obtiene el precio de venta del producto.
     *
     * @return El precio de venta del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de venta del producto.
     *
     * @param precio El nuevo precio de venta del producto.
     */
    public void setPrecio(double precio) {
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
     * Establece la lista de lotes asociados al producto.
     *
     * @param lotes La nueva lista de lotes asociados al producto.
     */
    public void setLotes(List<LoteProducto> lotes) {
        this.lotes = lotes;
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

    // Métodos adicionales
    /**
     * Agrega un lote a la lista de lotes del producto.
     *
     * @param lote El lote a agregar.
     */
    public void agregarLote(LoteProducto lote) {
        this.lotes.add(lote);
    }

    /**
     * Remueve un lote específico de la lista de lotes del producto.
     *
     * @param idLote El identificador del lote a remover.
     * @return true si el lote fue removido, false si no se encontró.
     */
    public boolean removerLote(long idLote) {
        return this.lotes.removeIf(lote -> lote.getId() == idLote);
    }

    /**
     * Obtiene la cantidad total disponible del producto sumando las cantidades
     * de todos los lotes.
     *
     * @return La cantidad total disponible del producto.
     */
    public int obtenerCantidadTotalDisponible() {
        return this.lotes.stream().mapToInt(LoteProducto::getCantidadDisponible).sum();
    }

    /**
     * Encuentra un lote por su identificador.
     *
     * @param idLote El identificador del lote a buscar.
     * @return El lote encontrado, o un Optional vacío si no se encuentra.
     */
    public Optional<LoteProducto> encontrarLotePorId(long idLote) {
        return this.lotes.stream().filter(lote -> lote.getId() == idLote).findFirst();
    }

    /**
     * Calcula el costo promedio ponderado del producto basado en los lotes
     * disponibles.
     *
     * @return El costo promedio ponderado del producto.
     */
    public double calcularCostoPromedioPonderado() {
        double costoTotal = 0;
        int cantidadTotal = 0;

        for (LoteProducto lote : this.lotes) {
            costoTotal += lote.getCosto() * lote.getCantidadDisponible();
            cantidadTotal += lote.getCantidadDisponible();
        }

        return cantidadTotal > 0 ? costoTotal / cantidadTotal : 0;
    }

    /**
     * Compara este producto con otro objeto para verificar si son iguales.
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

        Producto producto = (Producto) o;

        return id == producto.id;
    }

    /**
     * Genera un código hash para este producto basado en su identificador,
     * nombre y categoría.
     *
     * @return El código hash generado.
     */
    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        return result;
    }

    /**
     * Devuelve una representación en forma de cadena de este producto.
     *
     * @return Una cadena que representa el producto.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto {")
                .append("\n  id=").append(id)
                .append(",\n  nombre='").append(nombre).append('\'')
                .append(",\n  descripcion='").append(descripcion).append('\'')
                .append(",\n  precio=").append(precio)
                .append(",\n  lotes=").append(lotes)
                .append(",\n  categoria=").append(categoria)
                .append(",\n  estado=").append(estado)
                .append(",\n  fechaDeAdicion=").append(fechaDeAdicion)
                .append("\n}");
        return sb.toString();
    }
}
