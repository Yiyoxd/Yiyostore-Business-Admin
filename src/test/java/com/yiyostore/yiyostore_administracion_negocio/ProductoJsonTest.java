package com.yiyostore.yiyostore_administracion_negocio;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yiyostore.yiyostore_administracion_negocio.model.entity.Producto;
import com.yiyostore.yiyostore_administracion_negocio.repository.ProductoRepository;
import com.yiyostore.yiyostore_administracion_negocio.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductoJsonTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private String productoJson = "{\n"
            //+ "    \"id\": 1,\n"
            + "    \"nombre\": \"Producto1\",\n"
            + "    \"descripcion\": \"Descripción1\",\n"
            + "    \"precio\": 100.0,\n"
            + "    \"lotes\": [\n"
            + "        {\n"
            + "            \"costo\": 200.0,\n"
            + "            \"cantidadDisponible\": 20,\n"
            + "            \"linkDeCompra\": \"http://example.com/lote1\",\n"
            + "            \"notas\": \"Notas del lote 1\",\n"
            + "            \"estado\": \"NUEVO\"\n"
            + "        },\n"
            + "        {\n"
            + "            \"costo\": 300.0,\n"
            + "            \"cantidadDisponible\": 20,\n"
            + "            \"linkDeCompra\": \"http://example.com/lote2\",\n"
            + "            \"notas\": \"Notas del lote 2\",\n"
            + "            \"estado\": \"NUEVO\"\n"
            + "        }\n"
            + "    ],\n"
            + "    \"fechaDeAdicion\": \"2024-08-16\"\n"
            + "}";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalcularCostoPromedioPonderado() throws IOException {
        // Given
        Producto producto = leerProductoDesdeJson(productoJson);

        // Guardar el producto en el repositorio simulado
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // When
        double costoPromedio = productoService.calcularCostoPromedioPonderado(1L);

        // Then
        assertEquals(250.0, costoPromedio); // Actualiza el valor esperado si cambias los datos en JSON
        verify(productoRepository, times(1)).findById(1L);
    }

    private Producto leerProductoDesdeJson(String json) throws IOException {
        return objectMapper.readValue(json, Producto.class);
    }
}
