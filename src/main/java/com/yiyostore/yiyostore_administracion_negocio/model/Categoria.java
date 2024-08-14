package com.yiyostore.yiyostore_administracion_negocio.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa una categoría a la que pueden pertenecer los productos.
 */
@Entity
@Table(name = "categorias")
public class Categoria {

    /**
     * Identificador único de la categoría. Generado automáticamente por la base
     * de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Nombre de la categoría. Utiliza el enum {@link CategoriaEnum}.
     */
    @Column(name = "nombre", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private CategoriaEnum nombre;

    /**
     * Productos asociados a esta categoría.
     */
    @ManyToMany(mappedBy = "categorias")
    private Set<Producto> productos = new HashSet<>();

    /**
     * Constructor vacío requerido por JPA.
     */
    public Categoria() {
    }

    /**
     * Constructor para inicializar una categoría con su nombre.
     *
     * @param nombre Nombre de la categoría.
     */
    public Categoria(CategoriaEnum nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador único de la categoría.
     *
     * @return El identificador de la categoría.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la categoría.
     *
     * @param id El nuevo identificador de la categoría.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la categoría.
     *
     * @return El nombre de la categoría.
     */
    public CategoriaEnum getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la categoría.
     *
     * @param nombre El nuevo nombre de la categoría.
     */
    public void setNombre(CategoriaEnum nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los productos asociados a esta categoría.
     *
     * @return El conjunto de productos asociados.
     */
    public Set<Producto> getProductos() {
        return productos;
    }

    /**
     * Establece los productos asociados a esta categoría.
     *
     * @param productos El nuevo conjunto de productos asociados.
     */
    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Categoria{"
                + "id=" + id
                + ", nombre=" + nombre
                + '}';
    }
}
