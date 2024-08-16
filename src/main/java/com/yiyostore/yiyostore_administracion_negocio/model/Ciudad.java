package com.yiyostore.yiyostore_administracion_negocio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
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
     * Devuelve una representación en forma de cadena de la ciudad.
     *
     * @return Una cadena que representa la ciudad.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ciudad{")
                .append("id=").append(id)
                .append(", nombre='").append(nombre).append('\'')
                .append('}');
        return sb.toString();
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
        return Objects.equals(id, ciudad.id);
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
