package com.yiyostore.yiyostore_administracion_negocio.repository;

import com.yiyostore.yiyostore_administracion_negocio.model.enums.Estado;
import com.yiyostore.yiyostore_administracion_negocio.model.entity.LoteProducto;
import com.yiyostore.yiyostore_administracion_negocio.model.entity.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para acceder y manipular datos de la entidad LoteProducto en la
 * base de datos. Extiende JpaRepository para proporcionar métodos CRUD básicos
 * y soporte de paginación y clasificación.
 */
@Repository
public interface LoteProductoRepository extends JpaRepository<LoteProducto, Long> {

    List<LoteProducto> findByProductoAndEstadoInAndCantidadGreaterThanOrderByFechaAsc(Producto producto, List<Estado> estados, int cantidad);

    @Query("SELECT SUM(l.costo * l.cantidad) FROM LoteProducto l")
    double calcularCostoTotalInventario();
}
