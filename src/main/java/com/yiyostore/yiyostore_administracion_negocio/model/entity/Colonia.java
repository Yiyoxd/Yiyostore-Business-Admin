package com.yiyostore.yiyostore_administracion_negocio.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yiyostore.yiyostore_administracion_negocio.model.enums.Tipo;
import com.yiyostore.yiyostore_administracion_negocio.model.enums.TipoAsentamiento;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.util.Objects;

/**
 * Representa una Colonia con su información básica.
 */
@Entity
@Table(name = "colonias")
public class Colonia {

    /**
     * El identificador único de la colonia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * El código postal de la colonia.
     */
    @Column(name = "codigo_postal", nullable = false)
    private int codigoPostal;

    /**
     * El nombre de la colonia.
     */
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * La ciudad a la que pertenece la colonia.
     */
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ciudad_id", nullable = false)
    private Ciudad ciudad;

    /**
     * El tipo de asentamiento de la colonia (Colonia, Fraccionamiento, etc.).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_asentamiento", nullable = false)
    private TipoAsentamiento tipoAsentamiento;

    /**
     * El tipo de la colonia (Rural o Urbano).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private Tipo tipo;

    /**
     * Constructor por defecto para JPA.
     */
    public Colonia() {
    }

    /**
     * Constructor para crear una nueva Colonia.
     *
     * @param codigoPostal El código postal de la colonia.
     * @param nombre El nombre de la colonia.
     * @param ciudad La ciudad a la que pertenece la colonia.
     * @param tipoAsentamiento El tipo de asentamiento de la colonia.
     * @param tipo El tipo de la colonia (Rural o Urbano).
     */
    public Colonia(int codigoPostal, String nombre, Ciudad ciudad, TipoAsentamiento tipoAsentamiento, Tipo tipo) {
        this.codigoPostal = codigoPostal;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.tipoAsentamiento = tipoAsentamiento;
        this.tipo = tipo;
    }

    /**
     * Obtiene el identificador único de la colonia.
     *
     * @return El ID de la colonia.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la colonia.
     *
     * @param id El ID a establecer.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el código postal de la colonia.
     *
     * @return El código postal de la colonia.
     */
    public int getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Establece el código postal de la colonia.
     *
     * @param codigoPostal El código postal a establecer.
     */
    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Obtiene el nombre de la colonia.
     *
     * @return El nombre de la colonia.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la colonia.
     *
     * @param nombre El nombre a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la ciudad a la que pertenece la colonia.
     *
     * @return La ciudad a la que pertenece la colonia.
     */
    public Ciudad getCiudad() {
        return ciudad;
    }

    /**
     * Establece la ciudad a la que pertenece la colonia.
     *
     * @param ciudad La ciudad a establecer.
     */
    public void setCiudad(Ciudad ciudad) {
        if (this.ciudad != null && this.ciudad != ciudad) {
            this.ciudad.getColonias().remove(this);
        }
        this.ciudad = ciudad;
        if (ciudad != null && !ciudad.getColonias().contains(this)) {
            ciudad.getColonias().add(this);
        }
    }

    /**
     * Obtiene el tipo de asentamiento de la colonia.
     *
     * @return El tipo de asentamiento de la colonia.
     */
    public TipoAsentamiento getTipoAsentamiento() {
        return tipoAsentamiento;
    }

    /**
     * Establece el tipo de asentamiento de la colonia.
     *
     * @param tipoAsentamiento El tipo de asentamiento a establecer.
     */
    public void setTipoAsentamiento(TipoAsentamiento tipoAsentamiento) {
        this.tipoAsentamiento = tipoAsentamiento;
    }

    /**
     * Obtiene el tipo de la colonia (Rural o Urbano).
     *
     * @return El tipo de la colonia.
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de la colonia (Rural o Urbano).
     *
     * @param tipo El tipo a establecer.
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve una representación en cadena de la colonia.
     *
     * @return Una cadena con la información de la colonia.
     */
    @Override
    public String toString() {
        return String.format("Colonia{id=%d, codigoPostal=%d, nombre='%s', ciudad='%s', tipoAsentamiento='%s', tipo='%s'}",
                id, codigoPostal, nombre, ciudad.getNombre(), tipoAsentamiento.getDisplayName(), tipo.getDisplayName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigoPostal, nombre, ciudad, tipoAsentamiento, tipo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final Colonia otra = (Colonia) obj;
        return id.equals(otra.id);
    }
}
