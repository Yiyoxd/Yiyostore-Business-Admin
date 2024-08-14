package com.yiyostore.yiyostore_administracion_negocio.repository;


import com.yiyostore.yiyostore_administracion_negocio.model.ProductoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para manejar las operaciones CRUD de la entidad ProductoPedido.
 */
@Repository
public interface ProductoPedidoRepository extends JpaRepository<ProductoPedido, Long> {
}
