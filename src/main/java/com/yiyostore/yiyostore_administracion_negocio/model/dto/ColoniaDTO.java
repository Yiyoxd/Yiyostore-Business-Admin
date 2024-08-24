package com.yiyostore.yiyostore_administracion_negocio.model.dto;

/**
 * Record para transferir los datos de una Colonia junto con su Ciudad asociada.
 */
public record ColoniaDTO(
    Long id,
    String nombre,
    int codigoPostal,
    String nombreCiudad
) {}
