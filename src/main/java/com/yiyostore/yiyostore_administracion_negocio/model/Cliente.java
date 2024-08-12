package com.yiyostore.yiyostore_administracion_negocio.model;

import java.time.LocalDate;
import java.util.Objects;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
 
/**
 * Representa un cliente. Incluye todos
 * los detalles necesarios para administrar la relación con el cliente, como
 * identificación, información de contacto, compras realizadas, y otros
 * detalles relevantes.
 */
public class Cliente {

    private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                            
    /**
     * Identificador único del cliente asignado por el sistema.
     */
    private long id;

    /**
     * Nombre real del cliente.
     */
    private String nombre;

    /**
     * Nombre en Facebook del cliente.
     */
    private String nombreEnFacebook;

    /**
     * Dirección residencial del cliente.
     */
    private Direccion direccion;

    /**
     * Fecha en que el cliente hizo su primera compra.
     */
    private LocalDate fechaDePrimeraCompra;

    /**
     * Número de teléfono del cliente para llamadas y/o WhatsApp.
     */
    private String numeroTelefono;

    /**
     * Notas sobre el cliente.
     */
    private String notas;

    /**
     * Obtiene el identificador único del cliente.
     *
     * @return el identificador del cliente.
     */
    public long getId() {
        return id;
    }

    /**
     * Establece el identificador único del cliente.
     *
     * @param id el nuevo identificador del cliente.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre real del cliente.
     *
     * @return el nombre real del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre real del cliente.
     *
     * @param nombre el nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre en Facebook del cliente.
     *
     * @return el nombre en Facebook del cliente.
     */
    public String getNombreEnFacebook() {
        return nombreEnFacebook;
    }

    /**
     * Establece el nombre en Facebook del cliente.
     *
     * @param nombreEnFacebook el nuevo nombre en Facebook del cliente.
     */
    public void setNombreEnFacebook(String nombreEnFacebook) {
        this.nombreEnFacebook = nombreEnFacebook;
    }

    /**
     * Obtiene la dirección residencial del cliente.
     *
     * @return la dirección residencial del cliente.
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección residencial del cliente.
     *
     * @param direccion la nueva dirección del cliente.
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la fecha en que el cliente hizo su primera compra.
     *
     * @return la fecha de la primera compra del cliente.
     */
    public LocalDate getFechaDePrimeraCompra() {
        return fechaDePrimeraCompra;
    }

    /**
     * Establece la fecha en que el cliente hizo su primera compra.
     *
     * @param fechaDePrimeraCompra la nueva fecha de la primera compra del cliente.
     */
    public void setFechaDePrimeraCompra(LocalDate fechaDePrimeraCompra) {
        this.fechaDePrimeraCompra = fechaDePrimeraCompra;
    }

    /**
     * Obtiene el número de teléfono del cliente.
     *
     * @return el número de teléfono del cliente.
     */
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    /**
     * Establece el número de teléfono del cliente.
     * Este método valida el número de teléfono antes de asignarlo.
     *
     * @param numeroTelefono el nuevo número de teléfono del cliente.
     * @throws IllegalArgumentException si el número de teléfono no es válido.
     */
    public void setNumeroTelefono(String numeroTelefono) {
        if (!isValidPhoneNumber(numeroTelefono)) {
            throw new IllegalArgumentException("Número de teléfono inválido");
        }
        this.numeroTelefono = numeroTelefono;
    }

    /**
     * Obtiene las notas sobre el cliente.
     *
     * @return las notas sobre el cliente.
     */
    public String getNotas() {
        return notas;
    }

    /**
     * Establece las notas sobre el cliente.
     *
     * @param notas las nuevas notas sobre el cliente.
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente {")
          .append("ID=").append(id)
          .append(", Nombre='").append(nombre).append('\'')
          .append(", Nombre en Facebook='").append(nombreEnFacebook).append('\'')
          .append(", Dirección=").append(direccion)
          .append(", Fecha de Primera Compra=").append(fechaDePrimeraCompra)
          .append(", Número de Teléfono='").append(numeroTelefono).append('\'')
          .append(", Notas='").append(notas).append('\'')
          .append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, numeroTelefono);
    }

    /**
     * Valida el número de teléfono utilizando libphonenumber.
     *
     * @param numeroTelefono el número de teléfono a validar.
     * @return true si el número de teléfono es válido, false en caso contrario.
     */
    private boolean isValidPhoneNumber(String numeroTelefono) {
        try {
            Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(numeroTelefono, "MX");
            return phoneUtil.isValidNumber(phoneNumber);
        } catch (NumberParseException e) {
            return false;
        }
    }
}