package com.yiyostore.yiyostore_administracion_negocio.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yiyostore.yiyostore_administracion_negocio.exception.NumeroTelefonoInvalidoException;
import com.yiyostore.yiyostore_administracion_negocio.utils.TelefonoUtils;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa un cliente en el sistema. Esta clase incluye todos los detalles
 * necesarios para administrar la relación con el cliente, como identificación,
 * información de contacto, y otros detalles relevantes. Un cliente puede tener
 * múltiples pedidos asociados.
 */
@Entity
@Table(name = "clientes")
public class Cliente {

    /**
     * Identificador único del cliente asignado por el sistema. Este valor es
     * generado automáticamente por el sistema.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Nombre real del cliente. Este campo es opcional y puede ser nulo en caso
     * de que el cliente no proporcione un nombre. Se limita a 100 caracteres.
     */
    @Column(name = "nombre", length = 100, nullable = true)
    private String nombre;

    /**
     * Dirección residencial del cliente. Relación uno a uno con la entidad
     * {@link Direccion}. Este campo es opcional y puede ser nulo si el cliente
     * no proporciona una dirección.
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "direccion_id", nullable = true)
    private Direccion direccion;

    /**
     * Número de teléfono del cliente para llamadas y/o WhatsApp. Este campo es
     * opcional y puede ser nulo si el cliente no proporciona un número de
     * teléfono. Se limita a 20 caracteres y se valida antes de asignarlo.
     */
    @Column(name = "numero_telefono", length = 20, nullable = true)
    private String numeroTelefono;

    /**
     * Notas adicionales sobre el cliente. Este campo es opcional y puede
     * contener cualquier información adicional relevante sobre el cliente.
     */
    @Column(name = "notas", length = 255, nullable = true)
    private String notas;

    /**
     * Relación uno a muchos con la entidad {@link Pedido}. Un cliente puede
     * tener múltiples pedidos.
     */
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Pedido> pedidos = new ArrayList<>();

    /**
     * Constructor vacío necesario para JPA.
     */
    public Cliente() {
    }

    /**
     * Constructor completo para inicializar un cliente con todos sus atributos.
     *
     * @param nombre el nombre del cliente.
     * @param direccion la dirección del cliente.
     * @param numeroTelefono el número de teléfono del cliente.
     * @param notas notas adicionales sobre el cliente.
     */
    public Cliente(String nombre, Direccion direccion, String numeroTelefono, String notas) {
        this.nombre = nombre;
        this.direccion = direccion;
        setNumeroTelefono(numeroTelefono);
        this.notas = notas;
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
     * Establece el nombre del cliente.
     *
     * @param nombre el nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @throws NumeroTelefonoInvalidoException si el número de teléfono no es
     * válido.
     */
    public void setNumeroTelefono(String numeroTelefono) {
        if (numeroTelefono != null) {
            this.numeroTelefono = TelefonoUtils.normalizar(numeroTelefono);
        }
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
     * Obtiene la lista de pedidos asociados a este cliente.
     *
     * @return la lista de pedidos del cliente.
     */
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    /**
     * Agrega un pedido a la lista de pedidos del cliente. Mantiene la relación
     * bidireccional entre cliente y pedido.
     *
     * @param pedido el pedido a agregar.
     */
    public void agregarPedido(Pedido pedido) {
        pedido.setCliente(this);
        this.pedidos.add(pedido);
    }

    /**
     * Retorna una representación en forma de cadena de la instancia actual del
     * cliente.
     *
     * @return una cadena que representa al cliente.
     */
    @Override
    public String toString() {
        return String.format("Cliente {ID=%d, Nombre='%s, Número de Teléfono='%s', Notas='%s'}",
                id, nombre, numeroTelefono, notas);
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
        return Objects.equals(cliente.id, id);
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
}
