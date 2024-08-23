package com.yiyostore.yiyostore_administracion_negocio.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa una Ciudad.
 */
@Entity
@Table(name = "ciudades")
public class Ciudad {

    /**
     * Identificador único de la ciudad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Nombre de la ciudad.
     */
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    /**
     * Lista de colonias asociadas a la ciudad.
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Colonia> colonias = new ArrayList<>();

    /**
     * Constructor por defecto para JPA.
     */
    public Ciudad() {
    }

    /**
     * Constructor para crear una ciudad con un nombre específico.
     *
     * @param nombre El nombre de la ciudad.
     */
    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador único de la ciudad.
     *
     * @return El identificador único de la ciudad.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la ciudad.
     *
     * @param id El identificador único de la ciudad.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la ciudad.
     *
     * @return El nombre de la ciudad.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la ciudad.
     *
     * @param nombre El nombre de la ciudad.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la lista de colonias asociadas a la ciudad.
     *
     * @return Lista de colonias.
     */
    public List<Colonia> getColonias() {
        return colonias;
    }

    /**
     * Establece la lista de colonias asociadas a la ciudad.
     *
     * @param colonias Lista de colonias.
     */
    public void setColonias(List<Colonia> colonias) {
        this.colonias = colonias;
    }

    /**
     * Agrega una colonia a la lista de colonias de la ciudad.
     *
     * @param colonia La colonia a agregar.
     */
    public void agregarColonia(Colonia colonia) {
        if (colonia == null) {
            throw new IllegalArgumentException("La colonia no puede ser nula");
        }
        if (!this.colonias.contains(colonia)) {
            colonias.add(colonia);
            if (colonia.getCiudad() != this) {
                colonia.setCiudad(this);
            }
        }
    }

    /**
     * Elimina una colonia de la lista de colonias de la ciudad.
     *
     * @param colonia La colonia a eliminar.
     */
    public void eliminarColonia(Colonia colonia) {
        colonias.remove(colonia);
        colonia.setCiudad(null);
    }

    /**
     * Devuelve una representación en forma de cadena de la ciudad.
     *
     * @return Una cadena que representa la ciudad.
     */
    @Override
    public String toString() {
        return String.format("Ciudad{id=%d, nombre='%s, colonias'}", id, nombre, colonias);
    }

    /**
     * Compara este objeto Ciudad con otro para verificar si son iguales.
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
        Ciudad ciudad = (Ciudad) o;
        return id != null && id.equals(ciudad.id);
    }

    /**
     * Calcula el código hash de este objeto Ciudad.
     *
     * @return El código hash basado en el identificador único.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
