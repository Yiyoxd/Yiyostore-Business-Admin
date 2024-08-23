package com.yiyostore.yiyostore_administracion_negocio.model.enums;

/**
 * Enum que representa los posibles estados de un producto.
 * <p>
 * Cada estado tiene un nombre técnico que es utilizado en el código y un nombre
 * de visualización más amigable para ser mostrado en interfaces de usuario.
 * Esto permite que el estado se represente de una manera más adecuada para los
 * usuarios finales.
 * </p>
 */
public enum Estado {
    /**
     * Producto nuevo, sin uso previo.
     */
    NUEVO("Nuevo"),
    /**
     * Producto reacondicionado o restaurado para la venta.
     */
    REACONDICIONADO("Reacondicionado"),
    /**
     * Producto usado, con signos de desgaste pero en funcionamiento.
     */
    USADO("Usado"),
    /**
     * Producto devuelto por el cliente.
     */
    DEVUELTO("Devuelto"),
    /**
     * Producto defectuoso o con fallas.
     */
    DEFECTUOSO("Defectuoso"),
    /**
     * Producto que está siendo reparado.
     */
    EN_REPARACION("En Reparación"),
    /**
     * Producto que está siendo revisado para verificar su estado.
     */
    EN_REVISION("En Revisión");

    /**
     * Nombre amigable para mostrar al usuario final.
     */
    private final String displayName;

    /**
     * Constructor del enum que asigna un nombre de visualización a cada estado.
     *
     * @param displayName Nombre amigable que se utilizará en la interfaz de
     * usuario.
     */
    Estado(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Obtiene el nombre de visualización del estado.
     *
     * @return El nombre de visualización del estado.
     */
    public String getDisplayName() {
        return displayName;
    }
}
