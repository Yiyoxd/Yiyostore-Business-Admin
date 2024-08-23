package com.yiyostore.yiyostore_administracion_negocio.model.dto;

import java.util.Map;

public record ProductoInventarioDTO(Long id, String nombre, Map<String, Integer> cantidadesPorEstado) {}
