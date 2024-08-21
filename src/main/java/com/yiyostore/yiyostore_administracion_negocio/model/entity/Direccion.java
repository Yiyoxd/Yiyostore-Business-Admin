package com.yiyostore.yiyostore_administracion_negocio.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * La clase Direccion representa una dirección física detallada. Incluye
 * información como el nombre de la calle, número exterior e interior, colonia,
 * calles cercanas y detalles adicionales.
 */
@Entity
@Table(name = "direcciones")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Direccion {

    /**
     * Identificador único de la dirección, generado automáticamente por la base
     * de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Nombre de la calle. Este campo es opcional y puede ser nulo si la calle
     * no tiene nombre.
     */
    @Column(name = "nombre_calle", length = 100, nullable = true)
    private String nombreCalle;

    /**
     * Número exterior del domicilio. Este campo es opcional y puede ser nulo si
     * no hay número exterior.
     */
    @Column(name = "numero_exterior", length = 10, nullable = true)
    private String numeroExterior;

    /**
     * Número interior o apartamento. Puede ser nulo si no aplica.
     */
    @Column(name = "numero_interior", length = 10, nullable = true)
    private String numeroInterior;

    /**
     * Colonia o barrio donde se encuentra la dirección. Este campo es
     * obligatorio.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "colonia_id", nullable = false)
    private Colonia colonia;

    /**
     * Primera calle entre la que está ubicada la dirección. Este campo es
     * opcional.
     */
    @Column(name = "entre_calle_1", length = 100, nullable = true)
    private String entreCalle1;

    /**
     * Segunda calle entre la que está ubicada la dirección. Este campo es
     * opcional.
     */
    @Column(name = "entre_calle_2", length = 100, nullable = true)
    private String entreCalle2;

    /**
     * Detalles adicionales o referencia de la dirección. Este campo es
     * opcional.
     */
    @Column(name = "referencia", length = 255, nullable = true)
    private String referencia;

    /**
     * Constructor por defecto necesario para JPA.
     */
    public Direccion() {
    }

    /**
     * Constructor para inicializar una dirección con todos sus atributos.
     *
     * @param nombreCalle El nombre de la calle (puede ser null).
     * @param numeroExterior El número exterior del domicilio (puede ser null).
     * @param numeroInterior El número interior o apartamento (puede ser null).
     * @param colonia La colonia o barrio (no puede ser null).
     * @param entreCalle1 La primera calle entre la que está ubicada la
     * dirección (puede ser null).
     * @param entreCalle2 La segunda calle entre la que está ubicada la
     * dirección (puede ser null).
     * @param referencia Detalles adicionales o referencia de la dirección
     * (puede ser null).
     */
    public Direccion(String nombreCalle, String numeroExterior, String numeroInterior, Colonia colonia,
            String entreCalle1, String entreCalle2, String referencia) {
        this.nombreCalle = nombreCalle;
        this.numeroExterior = numeroExterior;
        this.numeroInterior = numeroInterior;
        setColonia(colonia);
        this.entreCalle1 = entreCalle1;
        this.entreCalle2 = entreCalle2;
        this.referencia = referencia;
    }

    /**
     * Obtiene el identificador único de la dirección.
     *
     * @return El identificador único de la dirección.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la dirección.
     *
     * @param id El identificador único de la dirección.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la calle.
     *
     * @return El nombre de la calle, o null si no tiene nombre.
     */
    public String getNombreCalle() {
        return nombreCalle;
    }

    /**
     * Establece el nombre de la calle.
     *
     * @param nombreCalle El nombre de la calle (puede ser null).
     */
    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    /**
     * Obtiene el número exterior del domicilio.
     *
     * @return El número exterior del domicilio, o null si no tiene número
     * exterior.
     */
    public String getNumeroExterior() {
        return numeroExterior;
    }

    /**
     * Establece el número exterior del domicilio.
     *
     * @param numeroExterior El número exterior del domicilio (puede ser null).
     */
    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    /**
     * Obtiene el número interior del domicilio.
     *
     * @return El número interior del domicilio, o null si no aplica.
     */
    public String getNumeroInterior() {
        return numeroInterior;
    }

    /**
     * Establece el número interior del domicilio.
     *
     * @param numeroInterior El número interior del domicilio (puede ser null).
     */
    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    /**
     * Obtiene la colonia o barrio donde se encuentra la dirección.
     *
     * @return La colonia o barrio.
     */
    public Colonia getColonia() {
        return colonia;
    }

    /**
     * Establece la colonia o barrio donde se encuentra la dirección.
     *
     * @param colonia La colonia o barrio (no puede ser null).
     * @throws IllegalArgumentException si la colonia es null.
     */
    public void setColonia(Colonia colonia) {
        if (colonia == null) {
            throw new IllegalArgumentException("La colonia no puede ser nula");
        }
        this.colonia = colonia;
    }

    /**
     * Obtiene la primera calle entre la que está ubicada la dirección.
     *
     * @return La primera calle entre la que está ubicada la dirección, o null
     * si no aplica.
     */
    public String getEntreCalle1() {
        return entreCalle1;
    }

    /**
     * Establece la primera calle entre la que está ubicada la dirección.
     *
     * @param entreCalle1 La primera calle entre la que está ubicada la
     * dirección (puede ser null).
     */
    public void setEntreCalle1(String entreCalle1) {
        this.entreCalle1 = entreCalle1;
    }

    /**
     * Obtiene la segunda calle entre la que está ubicada la dirección.
     *
     * @return La segunda calle entre la que está ubicada la dirección, o null
     * si no aplica.
     */
    public String getEntreCalle2() {
        return entreCalle2;
    }

    /**
     * Establece la segunda calle entre la que está ubicada la dirección.
     *
     * @param entreCalle2 La segunda calle entre la que está ubicada la
     * dirección (puede ser null).
     */
    public void setEntreCalle2(String entreCalle2) {
        this.entreCalle2 = entreCalle2;
    }

    /**
     * Obtiene los detalles adicionales o referencia de la dirección.
     *
     * @return Los detalles adicionales o referencia de la dirección, o null si
     * no aplica.
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Establece los detalles adicionales o referencia de la dirección.
     *
     * @param referencia Los detalles adicionales o referencia de la dirección
     * (puede ser null).
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * Devuelve una representación en forma de cadena de la dirección.
     *
     * @return Una cadena que representa la dirección.
     */
    @Override
    public String toString() {
        return String.format("Direccion{id=%d, nombreCalle='%s', numeroExterior='%s', numeroInterior='%s', colonia='%s', entreCalle1='%s', entreCalle2='%s', referencia='%s'}",
                id, nombreCalle != null ? nombreCalle : "N/A", numeroExterior != null ? numeroExterior : "N/A",
                numeroInterior != null ? numeroInterior : "N/A", colonia != null ? colonia.getNombre() : "N/A",
                entreCalle1 != null ? entreCalle1 : "N/A", entreCalle2 != null ? entreCalle2 : "N/A",
                referencia != null ? referencia : "N/A");
    }

    /**
     * Compara este objeto Direccion con otro para verificar si son iguales.
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
        Direccion direccion = (Direccion) o;
        return Objects.equals(id, direccion.id);
    }

    /**
     * Calcula el código hash de este objeto Direccion.
     *
     * @return El código hash basado en el identificador único.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
