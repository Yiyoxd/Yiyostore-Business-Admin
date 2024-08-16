package com.yiyostore.yiyostore_administracion_negocio;


import com.yiyostore.yiyostore_administracion_negocio.model.Estado;
import com.yiyostore.yiyostore_administracion_negocio.model.LoteProducto;
import com.yiyostore.yiyostore_administracion_negocio.model.Producto;
import com.yiyostore.yiyostore_administracion_negocio.repository.ProductoRepository;
import com.yiyostore.yiyostore_administracion_negocio.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodosLosProductos() {
        // Given
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Producto1", "Descripción1", 100.0, null, LocalDate.now()));
        productos.add(new Producto("Producto2", "Descripción2", 200.0, new ArrayList<>(), LocalDate.now()));
        when(productoRepository.findAll()).thenReturn(productos);

        // When
        List<Producto> result = productoService.obtenerTodosLosProductos();

        // Then
        assertEquals(2, result.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void testObtenerProductoPorId() {
        // Given
        Producto producto = new Producto("Producto1", "Descripción1", 100.0, new ArrayList<>(), LocalDate.now());
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // When
        Optional<Producto> result = productoService.obtenerProductoPorId(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Producto1", result.get().getNombre());
        verify(productoRepository, times(1)).findById(1L);
    }

    @Test
    void testCrearProducto() {
        // Given
        Producto producto = new Producto("Producto1", "Descripción1", 100.0, new ArrayList<>(), LocalDate.now());
        when(productoRepository.save(producto)).thenReturn(producto);

        // When
        Producto result = productoService.crearProducto(producto);

        // Then
        assertNotNull(result);
        assertEquals("Producto1", result.getNombre());
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void testActualizarProducto() {
        // Given
        Producto existingProducto = new Producto("Producto1", "Descripción1", 100.0, new ArrayList<>(), LocalDate.now());
        Producto updatedProducto = new Producto("ProductoUpdated", "DescripciónUpdated", 150.0, new ArrayList<>(), LocalDate.now());
        when(productoRepository.findById(1L)).thenReturn(Optional.of(existingProducto));
        when(productoRepository.save(existingProducto)).thenReturn(updatedProducto);

        // When
        Optional<Producto> result = productoService.actualizarProducto(1L, updatedProducto);

        // Then
        assertTrue(result.isPresent());
        assertEquals("ProductoUpdated", result.get().getNombre());
        verify(productoRepository, times(1)).findById(1L);
        verify(productoRepository, times(1)).save(existingProducto);
    }

    @Test
    void testEliminarProducto() {
        // Given
        when(productoRepository.existsById(1L)).thenReturn(true);

        // When
        boolean result = productoService.eliminarProducto(1L);

        // Then
        assertTrue(result);
        verify(productoRepository, times(1)).existsById(1L);
        verify(productoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testObtenerCantidadTotalDisponible() {
        // Given
        Producto producto = new Producto("Producto1", "Descripción1", 100.0, new ArrayList<>(), LocalDate.now());
        // Agregar lotes y cantidades disponibles a la lista de lotes
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // When
        int cantidadTotal = productoService.obtenerCantidadTotalDisponible(1L);

        // Then
        assertEquals(0, cantidadTotal); // Basado en el estado inicial del producto sin lotes
        verify(productoRepository, times(1)).findById(1L);
    }

    @Test
    void testCalcularCostoPromedioPonderado() {
        // Given
        Producto producto = new Producto("Producto1", "Descripción1", 100.0, null, LocalDate.now());
        
        guardarLotes(producto);

        // When
        double costoPromedio = productoService.calcularCostoPromedioPonderado(1L);

        // Then
        assertEquals(250.0, costoPromedio); // Basado en el estado inicial del producto sin lotes
        verify(productoRepository, times(1)).findById(1L);
    }
    
    private void guardarLotes(Producto producto) {
        List<LoteProducto> lotes = new ArrayList();
        lotes.add(new LoteProducto(200.0, 20, producto, null, null, Estado.NUEVO, null));
        lotes.add(new LoteProducto(300.0, 20, producto, null, null, Estado.NUEVO, null));
        
        producto.setLotes(lotes);
        // Agregar lotes con costos y cantidades
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
    }
}
