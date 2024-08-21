package com.yiyostore.yiyostore_administracion_negocio.model.entity;

import com.yiyostore.yiyostore_administracion_negocio.model.enums.CategoriaEnum;
import jakarta.persistence.*;

/**
 * Representa una categoría a la que pueden pertenecer los productos. Se eliminó
 * la relación con productos para simplificar el modelo.
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
     * Devuelve una representación en forma de cadena de esta categoría.
     *
     * @return Una cadena que representa la categoría.
     */
    @Override
    public String toString() {
        return "Categoria{"
                + "id=" + id
                + ", nombre=" + nombre
                + '}';
    }

    /**
     * Compara esta categoría con otro objeto para verificar si son iguales.
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
        Categoria categoria = (Categoria) o;
        return id != null ? id.equals(categoria.id) : categoria.id == null;
    }

    /**
     * Genera un código hash para esta categoría basado en su identificador.
     *
     * @return El código hash generado.
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
