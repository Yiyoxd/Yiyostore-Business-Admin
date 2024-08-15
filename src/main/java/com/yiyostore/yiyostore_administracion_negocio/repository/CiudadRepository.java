package com.yiyostore.yiyostore_administracion_negocio.repository;

import com.yiyostore.yiyostore_administracion_negocio.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Ciudad. Proporciona métodos CRUD básicos y
 * permite la implementación de consultas personalizadas.
 */
@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
}
