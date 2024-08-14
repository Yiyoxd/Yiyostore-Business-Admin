package com.yiyostore.yiyostore_administracion_negocio.service;

import com.yiyostore.yiyostore_administracion_negocio.model.Cliente;
import com.yiyostore.yiyostore_administracion_negocio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para manejar la lógica de negocio relacionada con los clientes.
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Obtiene todos los clientes de la base de datos.
     *
     * @return Lista de todos los clientes.
     */
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    /**
     * Busca un cliente por su ID.
     *
     * @param id ID del cliente.
     * @return Un Optional que contiene el cliente si se encuentra, o vacío si
     * no se encuentra.
     */
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    /**
     * Guarda un nuevo cliente en la base de datos.
     *
     * @param cliente El cliente a guardar.
     * @return El cliente guardado.
     */
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Actualiza un cliente existente.
     *
     * @param id ID del cliente a actualizar.
     * @param cliente Los nuevos datos del cliente.
     * @return Un Optional con el cliente actualizado si el cliente existe, o
     * vacío si no existe.
     */
    public Optional<Cliente> update(Long id, Cliente cliente) {
        if (clienteRepository.existsById(id)) {
            cliente.setId(id);
            return Optional.of(clienteRepository.save(cliente));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Elimina un cliente por su ID.
     *
     * @param id ID del cliente a eliminar.
     * @return true si el cliente fue eliminado, false si no se encontró.
     */
    public boolean delete(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
