package com.yiyostore.yiyostore_administracion_negocio.model;

import java.util.Objects;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Representa un cliente en el sistema. Esta clase incluye todos
 * los detalles necesarios para administrar la relación con el cliente, como
 * identificación, información de contacto, y otros
 * detalles relevantes.
 */
@Entity
@Table(name = "clientes")
public class Cliente {

    private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    /**
     * Identificador único del cliente asignado por el sistema.
     * Este valor es generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Nombre real del cliente.
     * Este campo es opcional y puede ser nulo en caso de que el cliente no proporcione un nombre.
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Nombre del cliente en redes sociales.
     * Este campo es opcional y se utiliza para almacenar el nombre del cliente en plataformas de redes sociales.
     */
    @Column(name = "nombre_en_redes")
    private String nombreEnRedes;

    /**
     * Dirección residencial del cliente.
     * Relación uno a uno con la entidad {@link Direccion}. Esta relación permite que cada cliente tenga una única dirección asociada.
     * La dirección se carga de manera perezosa (lazy) y todas las operaciones de persistencia realizadas en el cliente
     * se propagarán a la dirección.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Direccion direccion;

    /**
     * Número de teléfono del cliente para llamadas y/o WhatsApp.
     * Este campo es opcional y puede ser nulo si el cliente no proporciona un número de teléfono.
     * Se realiza una validación del número de teléfono antes de asignarlo, utilizando la biblioteca libphonenumber.
     */
    @Column(name = "numero_telefono")
    private String numeroTelefono;

    /**
     * Notas adicionales sobre el cliente.
     * Este campo es opcional y puede contener cualquier información adicional relevante sobre el cliente.
     */
    @Column(name = "notas")
    private String notas;

    // Constructor por defecto
    public Cliente() {
        // Constructor vacío necesario para JPA
    }

    // Constructor para cliente por defecto
    public Cliente(boolean esClientePorDefecto) {
        if (esClientePorDefecto) {
            this.nombre = "Cliente Desconocido";
            this.nombreEnRedes = "NA";
            this.direccion = null;
            this.numeroTelefono = "NA";
            this.notas = "Cliente por defecto para ventas a desconocidos.";
        }
    }

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
     * @return el nombre real del cliente, o null si no tiene un nombre registrado.
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
     * Obtiene el nombre del cliente en redes sociales.
     *
     * @return el nombre en redes del cliente.
     */
    public String getNombreEnRedes() {
        return nombreEnRedes;
    }

    /**
     * Establece el nombre del cliente en redes sociales.
     *
     * @param nombreEnRedes el nuevo nombre en redes del cliente.
     */
    public void setNombreEnRedes(String nombreEnRedes) {
        this.nombreEnRedes = nombreEnRedes;
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
     * Obtiene el número de teléfono del cliente.
     *
     * @return el número de teléfono del cliente, o null si no tiene un número registrado.
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
        if (numeroTelefono != null && !isValidPhoneNumber(numeroTelefono)) {
            throw new IllegalArgumentException("Número de teléfono inválido");
        }
        this.numeroTelefono = numeroTelefono;
    }

    /**
     * Obtiene las notas adicionales sobre el cliente.
     *
     * @return las notas adicionales sobre el cliente.
     */
    public String getNotas() {
        return notas;
    }

    /**
     * Establece las notas adicionales sobre el cliente.
     *
     * @param notas las nuevas notas sobre el cliente.
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * Retorna una representación en forma de cadena de la instancia actual del cliente.
     *
     * @return una cadena que representa al cliente.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente {")
          .append("ID=").append(id)
          .append(", Nombre='").append(nombre).append('\'')
          .append(", Nombre en Redes='").append(nombreEnRedes).append('\'')
          .append(", Dirección=").append(direccion)
          .append(", Número de Teléfono='").append(numeroTelefono).append('\'')
          .append(", Notas='").append(notas).append('\'')
          .append('}');
        return sb.toString();
    }

    /**
     * Determina si este cliente es igual a otro objeto.
     *
     * @param o el objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id;
    }

    /**
     * Retorna un valor hash para el cliente.
     *
     * @return el valor hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, numeroTelefono);
    }

    /**
     * Valida el número de teléfono utilizando la biblioteca libphonenumber.
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

    /**
     * Crea y devuelve una instancia de cliente por defecto.
     *
     * @return una instancia de cliente por defecto.
     */
    public static Cliente crearClientePorDefecto() {
        return new Cliente(true);
    }
}