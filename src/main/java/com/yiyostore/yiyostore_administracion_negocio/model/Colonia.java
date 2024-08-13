package com.yiyostore.yiyostore_administracion_negocio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.util.Objects;

/**
 * Representa una Colonia con su información básica.
 */
@Entity
@Table(name = "colonias")
public class Colonia {

    /**
     * El código postal de la colonia.
     */
    @Id
    @Column(name = "codigo_postal", nullable = false)
    private int codigoPostal;

    /**
     * El nombre de la colonia.
     */
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * El tipo de la colonia (Rural o Urbano).
     */
    @Column(name = "tipo", nullable = false)
    private TipoColonia tipo;

    /**
     * Constructor para crear una nueva Colonia.
     *
     * @param codigoPostal El código postal de la colonia.
     * @param nombre El nombre de la colonia.
     * @param tipo El tipo de la colonia (Rural o Urbano).
     */
    public Colonia(int codigoPostal, String nombre, TipoColonia tipo) {
        this.codigoPostal = codigoPostal;
        this.nombre = nombre;
        this.tipo = tipo;
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
     * Obtiene el tipo de la colonia.
     *
     * @return El tipo de la colonia (Rural o Urbano).
     */
    public TipoColonia getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de la colonia.
     *
     * @param tipo El tipo a establecer (Rural o Urbano).
     */
    public void setTipo(TipoColonia tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve una representación en cadena de la colonia.
     *
     * @return Una cadena con la información de la colonia.
     */
    @Override
    public String toString() {
        return "Colonia{"
                + "codigoPostal=" + codigoPostal
                + ", nombre='" + nombre + '\''
                + ", tipo=" + tipo
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + codigoPostal;
        hash = 79 * hash + Objects.hashCode(nombre);
        hash = 79 * hash + Objects.hashCode(tipo);
        return hash;
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
        return nombre.equalsIgnoreCase(otra.nombre)
                && codigoPostal == otra.codigoPostal
                && tipo == otra.tipo;
    }

    /**
     * Enum para representar los tipos de colonias.
     */
    public enum TipoColonia {
        RURAL, URBANO
    }
}
