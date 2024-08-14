package com.yiyostore.yiyostore_administracion_negocio.model;

import java.util.Objects;
import com.yiyostore.yiyostore_administracion_negocio.utils.ValidarNumero;
import jakarta.persistence.*;

/**
 * Representa un cliente en el sistema. Esta clase incluye todos los detalles
 * necesarios para administrar la relación con el cliente, como identificación,
 * información de contacto, y otros detalles relevantes.
 */
@Entity
@Table(name = "clientes")
public class Cliente {

    /**
     * Identificador único del cliente asignado por el sistema. Este valor es
     * generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Nombre real del cliente. Este campo es opcional y puede ser nulo en caso
     * de que el cliente no proporcione un nombre.
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Nombre del cliente en redes sociales. Este campo es opcional y se utiliza
     * para almacenar el nombre del cliente en plataformas de redes sociales.
     */
    @Column(name = "nombre_en_redes")
    private String nombreEnRedes;

    /**
     * Dirección residencial del cliente. Relación uno a uno con la entidad
     * {@link Direccion}. Esta relación permite que cada cliente tenga una única
     * dirección asociada. La dirección se carga de manera perezosa (lazy) y
     * todas las operaciones de persistencia realizadas en el cliente se
     * propagarán a la dirección.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "direccion_id") // Especifica la columna de la clave foránea
    private Direccion direccion;

    /**
     * Número de teléfono del cliente para llamadas y/o WhatsApp. Este campo es
     * opcional y puede ser nulo si el cliente no proporciona un número de
     * teléfono. Se realiza una validación del número de teléfono antes de
     * asignarlo, utilizando la biblioteca libphonenumber.
     */
    @Column(name = "numero_telefono")
    private String numeroTelefono;

    /**
     * Notas adicionales sobre el cliente. Este campo es opcional y puede
     * contener cualquier información adicional relevante sobre el cliente.
     */
    @Column(name = "notas")
    private String notas;

    /**
     * Constructor vacío necesario para JPA.
     */
    public Cliente() {
    }

    /**
     * Constructor para cliente por defecto.
     *
     * @param esClientePorDefecto {@code true} si es un cliente por defecto.
     */
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
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del cliente.
     *
     * @param id el nuevo identificador del cliente.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre real del cliente.
     *
     * @return el nombre real del cliente, o null si no tiene un nombre
     * registrado.
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
     * @return el número de teléfono del cliente, o null si no tiene un número
     * registrado.
     */
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    /**
     * Establece el número de teléfono del cliente. Este método valida el número
     * de teléfono antes de asignarlo.
     *
     * @param numeroTelefono el nuevo número de teléfono del cliente.
     * @throws IllegalArgumentException si el número de teléfono no es válido.
     */
    public void setNumeroTelefono(String numeroTelefono) {
        if (numeroTelefono != null && !ValidarNumero.esValido(numeroTelefono)) {
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
     * Retorna una representación en forma de cadena de la instancia actual del
     * cliente.
     *
     * @return una cadena que representa al cliente.
     */
    @Override
    public String toString() {
        return String.format("Cliente {ID=%d, Nombre='%s', Nombre en Redes='%s', Dirección=%s, Número de Teléfono='%s', Notas='%s'}",
                id, nombre, nombreEnRedes, direccion, numeroTelefono, notas);
    }

    /**
     * Determina si este cliente es igual a otro objeto.
     *
     * @param o el objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
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
     * Crea y devuelve una instancia de cliente por defecto.
     *
     * @return una instancia de cliente por defecto.
     */
    public static Cliente crearClientePorDefecto() {
        return new Cliente(true);
    }
}
