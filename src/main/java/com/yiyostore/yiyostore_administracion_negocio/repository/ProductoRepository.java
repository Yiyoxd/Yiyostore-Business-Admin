package com.yiyostore.yiyostore_administracion_negocio.repository;

import com.yiyostore.yiyostore_administracion_negocio.model.entity.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para acceder y manipular datos de la entidad Producto en la base
 * de datos. Extiende JpaRepository para proporcionar métodos CRUD básicos y
 * soporte de paginación y clasificación.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT SUM(p.precio * l.cantidad) FROM LoteProducto l JOIN l.producto p WHERE l.cantidad > 0")
    double calcularPrecioVentaTotalInventario();

    @Query("SELECT p FROM Producto p LEFT JOIN FETCH p.lotes l WHERE l.cantidad > 0 OR l IS NULL")
    List<Producto> findAllWithLotesHavingQuantityGreaterThanZeroOrNoLotes();
}
