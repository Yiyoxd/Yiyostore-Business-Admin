package com.yiyostore.yiyostore_administracion_negocio.model.enums;

/**
 * Enum para representar si es Rural o Urbana.
 */
public enum Tipo {
    RURAL("Rural"),
    URBANO("Urbano");

    private final String displayName;

    Tipo(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
