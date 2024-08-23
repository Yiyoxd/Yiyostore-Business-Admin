package com.yiyostore.yiyostore_administracion_negocio.controller;

import com.yiyostore.yiyostore_administracion_negocio.model.entity.Cliente;
import com.yiyostore.yiyostore_administracion_negocio.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para manejar las operaciones relacionadas con los clientes.
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /**
     * Obtiene la lista de todos los clientes.
     *
     * @return Lista de clientes.
     */
    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.findAll();
    }

    /**
     * Obtiene un cliente por su ID.
     *
     * @param id ID del cliente.
     * @return Respuesta con el cliente encontrado o un estado 404 si no se
     * encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo cliente.
     *
     * @param cliente Datos del cliente a crear.
     * @return El cliente creado.
     */
    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    /**
     * Actualiza un cliente existente.
     *
     * @param id ID del cliente a actualizar.
     * @param cliente Nuevos datos del cliente.
     * @return Respuesta con el cliente actualizado o un estado 404 si no se
     * encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> updatedCliente = clienteService.update(id, cliente);
        return updatedCliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Elimina un cliente por su ID.
     *
     * @param id ID del cliente a eliminar.
     * @return Respuesta con un estado 204 si se elimina o un estado 404 si no
     * se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        boolean deleted = clienteService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
