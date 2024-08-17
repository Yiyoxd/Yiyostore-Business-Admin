package com.yiyostore.yiyostore_administracion_negocio;

import com.yiyostore.yiyostore_administracion_negocio.model.LoteProducto;
import com.yiyostore.yiyostore_administracion_negocio.model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductoTest {

    private Producto producto;
    private LoteProducto lote1;
    private LoteProducto lote2;

    @BeforeEach
    public void setUp() {
        // Inicialización de datos para pruebas
        lote1 = Mockito.mock(LoteProducto.class);
        lote2 = Mockito.mock(LoteProducto.class);

        Mockito.when(lote1.getCantidad()).thenReturn(10);
        Mockito.when(lote1.getCosto()).thenReturn(100.0);
        Mockito.when(lote2.getCantidad()).thenReturn(20);
        Mockito.when(lote2.getCosto()).thenReturn(150.0);

        List<LoteProducto> lotes = new ArrayList<>();
        lotes.add(lote1);
        lotes.add(lote2);

        producto = new Producto("Producto A", "Descripción del Producto A", 200.0, lotes, LocalDate.now());
    }

    @Test
    public void testGettersAndSetters() {
        assertThat(producto.getNombre()).isEqualTo("Producto A");
        assertThat(producto.getDescripcion()).isEqualTo("Descripción del Producto A");
        assertThat(producto.getPrecio()).isEqualTo(200.0);
        assertThat(producto.getLotes()).hasSize(2);
        assertThat(producto.getFechaDeAdicion()).isNotNull();
    }

    @Test
    public void testAgregarLote() {
        LoteProducto loteNuevo = Mockito.mock(LoteProducto.class);
        producto.agregarLote(loteNuevo);

        assertThat(producto.getLotes()).contains(loteNuevo);
    }

    @Test
    public void testRemoverLote() {
        boolean removed = producto.removerLote(lote1.getId());

        assertThat(removed).isTrue();
        assertThat(producto.getLotes()).doesNotContain(lote1);
    }
}