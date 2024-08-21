package com.yiyostore.yiyostore_administracion_negocio.model.enums;

/**
 * Enum para representar los tipos de asentamiento de las colonias.
 */
public enum TipoAsentamiento {
    COLONIA("Colonia"),
    FRACCIONAMIENTO("Fraccionamiento"),
    EJIDO("Ejido"),
    RANCHERÍA("Ranchería"),
    GRANJA("Granja"),
    RANCHO("Rancho"),
    BARRIO("Barrio"),
    UNIDAD_HABITACIONAL("Unidad Habitacional"),
    ZONA_COMERCIAL("Zona Comercial"),
    ZONA_INDUSTRIAL("Zona Industrial"),
    EQUIPAMIENTO("Equipamiento"),
    PARAJE("Paraje"),
    PUEBLO("Pueblo"),
    OTRO("Otro");

    private final String displayName;

    TipoAsentamiento(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
