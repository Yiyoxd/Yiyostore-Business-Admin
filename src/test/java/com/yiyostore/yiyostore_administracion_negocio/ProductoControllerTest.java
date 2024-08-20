package com.yiyostore.yiyostore_administracion_negocio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yiyostore.yiyostore_administracion_negocio.controller.ProductoController;
import com.yiyostore.yiyostore_administracion_negocio.model.Producto;
import com.yiyostore.yiyostore_administracion_negocio.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    private Producto producto;
    
    ObjectMapper objectMapper = new ObjectMapper(); 

    @AfterEach
    void sout() {
        try {
            // Convertir el objeto en JSON
            String json = objectMapper.writeValueAsString(producto);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        producto = new Producto("Test Producto", "Descripción del producto", 100.0, null);
        producto.setId(1L);
    }

    @Test
    void testObtenerTodosLosProductos() {
        List<Producto> productos = Arrays.asList(producto);
        when(productoService.obtenerTodosLosProductos()).thenReturn(productos);

        List<Producto> resultado = productoController.obtenerTodosLosProductos();

        assertEquals(1, resultado.size());
        assertEquals(producto.getNombre(), resultado.get(0).getNombre());
        verify(productoService, times(1)).obtenerTodosLosProductos();
    }

    @Test
    void testObtenerProductoPorId() {
        when(productoService.obtenerProductoPorId(1L)).thenReturn(Optional.of(producto));

        ResponseEntity<Producto> respuesta = productoController.obtenerProductoPorId(1L);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(producto.getNombre(), respuesta.getBody().getNombre());
        verify(productoService, times(1)).obtenerProductoPorId(1L);
    }

    @Test
    void testObtenerProductoPorId_NotFound() {
        when(productoService.obtenerProductoPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Producto> respuesta = productoController.obtenerProductoPorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
        verify(productoService, times(1)).obtenerProductoPorId(1L);
    }

    @Test
    void testCrearProducto() {
        when(productoService.crearProducto(producto)).thenReturn(producto);

        Producto productoCreado = productoController.crearProducto(producto);

        assertEquals(producto.getNombre(), productoCreado.getNombre());
        verify(productoService, times(1)).crearProducto(producto);
    }

    @Test
    void testActualizarProducto() {
        when(productoService.actualizarProducto(1L, producto)).thenReturn(Optional.of(producto));

        ResponseEntity<Producto> respuesta = productoController.actualizarProducto(1L, producto);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(producto.getNombre(), respuesta.getBody().getNombre());
        verify(productoService, times(1)).actualizarProducto(1L, producto);
    }

    @Test
    void testActualizarProducto_NotFound() {
        when(productoService.actualizarProducto(1L, producto)).thenReturn(Optional.empty());

        ResponseEntity<Producto> respuesta = productoController.actualizarProducto(1L, producto);

        assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
        verify(productoService, times(1)).actualizarProducto(1L, producto);
    }

    @Test
    void testEliminarProducto() {
        when(productoService.eliminarProducto(1L)).thenReturn(true);

        ResponseEntity<Void> respuesta = productoController.eliminarProducto(1L);

        assertEquals(HttpStatus.NO_CONTENT, respuesta.getStatusCode());
        verify(productoService, times(1)).eliminarProducto(1L);
    }

    @Test
    void testEliminarProducto_NotFound() {
        when(productoService.eliminarProducto(1L)).thenReturn(false);

        ResponseEntity<Void> respuesta = productoController.eliminarProducto(1L);

        assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
        verify(productoService, times(1)).eliminarProducto(1L);
    }
}
