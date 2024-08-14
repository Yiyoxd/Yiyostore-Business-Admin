package com.yiyostore.yiyostore_administracion_negocio.repository;

import com.yiyostore.yiyostore_administracion_negocio.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para acceder y manipular datos de la entidad Cliente en la base
 * de datos. Extiende JpaRepository para proporcionar métodos CRUD básicos y
 * soporte de paginación y clasificación.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Puedes definir métodos personalizados adicionales aquí si es necesario
}
