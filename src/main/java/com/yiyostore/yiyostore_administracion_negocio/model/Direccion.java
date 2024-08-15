package com.yiyostore.yiyostore_administracion_negocio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.Objects;

/**
 * La clase Direccion representa una dirección física detallada, incluyendo
 * nombre de la calle, número exterior e interior, colonia y calles cercanas,
 * junto con detalles adicionales.
 */
@Entity
@Table(name = "direcciones")
public class Direccion {

    /**
     * Identificador único de la dirección.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Nombre de la calle.
     */
    @Column(name = "nombre_calle", length = 100)
    private String nombreCalle;

    /**
     * Número exterior del domicilio.
     */
    @Column(name = "numero_exterior", length = 10)
    private String numeroExterior;

    /**
     * Número interior o apartamento. Puede ser nulo si no aplica.
     */
    @Column(name = "numero_interior", length = 10)
    private String numeroInterior;

    /**
     * Colonia o barrio donde se encuentra la dirección.
     */
    @ManyToOne
    @JoinColumn(name = "colonia_id", nullable = false)
    private Colonia colonia;

    /**
     * Primera calle entre la que está ubicada la dirección.
     */
    @Column(name = "entre_calle_1", length = 100)
    private String entreCalle1;

    /**
     * Segunda calle entre la que está ubicada la dirección.
     */
    @Column(name = "entre_calle_2", length = 100)
    private String entreCalle2;

    /**
     * Detalles adicionales o referencia de la dirección.
     */
    @Column(name = "referencia", length = 255)
    private String referencia;

    /**
     * Constructor por defecto necesario para JPA.
     */
    public Direccion() {
    }

    /**
     * Constructor para inicializar una dirección con todos sus atributos.
     *
     * @param id El identificador único de la dirección.
     * @param nombreCalle El nombre de la calle.
     * @param numeroExterior El número exterior del domicilio.
     * @param numeroInterior El número interior o apartamento (puede ser null).
     * @param colonia La colonia o barrio.
     * @param entreCalle1 La primera calle entre la que está ubicada la
     * dirección.
     * @param entreCalle2 La segunda calle entre la que está ubicada la
     * dirección.
     * @param referencia Detalles adicionales o referencia de la dirección.
     */
    public Direccion(Long id, String nombreCalle, String numeroExterior, String numeroInterior, Colonia colonia,
            String entreCalle1, String entreCalle2, String referencia) {
        this.id = id;
        this.nombreCalle = nombreCalle;
        this.numeroExterior = numeroExterior;
        this.numeroInterior = numeroInterior;
        this.colonia = colonia;
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
     * @return El nombre de la calle.
     */
    public String getNombreCalle() {
        return nombreCalle;
    }

    /**
     * Establece el nombre de la calle.
     *
     * @param nombreCalle El nombre de la calle.
     */
    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    /**
     * Obtiene el número exterior del domicilio.
     *
     * @return El número exterior del domicilio.
     */
    public String getNumeroExterior() {
        return numeroExterior;
    }

    /**
     * Establece el número exterior del domicilio.
     *
     * @param numeroExterior El número exterior del domicilio.
     */
    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    /**
     * Obtiene el número interior del domicilio.
     *
     * @return El número interior del domicilio.
     */
    public String getNumeroInterior() {
        return numeroInterior;
    }

    /**
     * Establece el número interior del domicilio.
     *
     * @param numeroInterior El número interior del domicilio.
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
     * @param colonia La colonia o barrio.
     */
    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }

    /**
     * Obtiene la primera calle entre la que está ubicada la dirección.
     *
     * @return La primera calle entre la que está ubicada la dirección.
     */
    public String getEntreCalle1() {
        return entreCalle1;
    }

    /**
     * Establece la primera calle entre la que está ubicada la dirección.
     *
     * @param entreCalle1 La primera calle entre la que está ubicada la
     * dirección.
     */
    public void setEntreCalle1(String entreCalle1) {
        this.entreCalle1 = entreCalle1;
    }

    /**
     * Obtiene la segunda calle entre la que está ubicada la dirección.
     *
     * @return La segunda calle entre la que está ubicada la dirección.
     */
    public String getEntreCalle2() {
        return entreCalle2;
    }

    /**
     * Establece la segunda calle entre la que está ubicada la dirección.
     *
     * @param entreCalle2 La segunda calle entre la que está ubicada la
     * dirección.
     */
    public void setEntreCalle2(String entreCalle2) {
        this.entreCalle2 = entreCalle2;
    }

    /**
     * Obtiene los detalles adicionales o referencia de la dirección.
     *
     * @return Los detalles adicionales o referencia de la dirección.
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Establece los detalles adicionales o referencia de la dirección.
     *
     * @param referencia Los detalles adicionales o referencia de la dirección.
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
        return "Direccion{"
                + "id=" + id
                + ", nombreCalle='" + nombreCalle + '\''
                + ", numeroExterior='" + numeroExterior + '\''
                + ", numeroInterior='" + numeroInterior + '\''
                + ", colonia=" + colonia
                + ", entreCalle1='" + entreCalle1 + '\''
                + ", entreCalle2='" + entreCalle2 + '\''
                + ", referencia='" + referencia + '\''
                + '}';
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
