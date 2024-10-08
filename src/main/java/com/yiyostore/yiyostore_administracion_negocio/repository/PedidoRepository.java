package com.yiyostore.yiyostore_administracion_negocio.repository;

import com.yiyostore.yiyostore_administracion_negocio.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para acceder y manipular datos de la entidad Pedido en la base de
 * datos. Extiende JpaRepository para proporcionar métodos CRUD básicos y
 * soporte de paginación y clasificación.
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {}
