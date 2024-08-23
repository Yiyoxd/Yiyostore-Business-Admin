package com.yiyostore.yiyostore_administracion_negocio.model.enums;

/**
 * Enum que representa las categorías disponibles para los productos con nombres
 * legibles.
 */
public enum CategoriaEnum {
    HERRAMIENTAS_ELECTRICAS("Herramientas Eléctricas"),
    HERRAMIENTAS_MANUALES("Herramientas Manuales"),
    ACCESORIOS("Accesorios"),
    EQUIPOS_DE_SEGURIDAD("Equipos de Seguridad"),
    MAQUINARIA("Maquinaria"),
    OTROS("Otros"),
    PINTURAS_Y_ADHESIVOS("Pinturas y Adhesivos"),
    ILUMINACION("Iluminación"),
    JARDINERIA("Jardinería"),
    FONTANERIA("Fontanería"),
    ELECTRICIDAD("Electricidad"),
    CONSTRUCCION("Construcción"),
    FERRETERIA("Ferretería"),
    MATERIALES_DE_CONSTRUCCION("Materiales de Construcción"),
    HOGAR_Y_DECORACION("Hogar y Decoración"),
    HERRAMIENTAS_DE_MEDICION("Herramientas de Medición"),
    CORTADORAS("Cortadoras"),
    SUJETADORES_Y_ABRAZADERAS("Sujetadores y Abrazaderas"),
    HERRAMIENTAS_DE_CORTE("Herramientas de Corte"),
    HERRAMIENTAS_DE_GOLPE("Herramientas de Golpe"),
    HERRAMIENTAS_DE_SUJECION("Herramientas de Sujeción"),
    HERRAMIENTAS_DE_PRUEBA_Y_MEDICION("Herramientas de Prueba y Medición"),
    HERRAMIENTAS_DE_SUCCION("Herramientas de Succión"),
    HERRAMIENTAS_DE_PINTURA("Herramientas de Pintura"),
    HERRAMIENTAS_DE_SOLDADURA("Herramientas de Soldadura"),
    HERRAMIENTAS_NEUMATICAS("Herramientas Neumáticas"),
    HERRAMIENTAS_DE_BATERIA("Herramientas de Batería"),
    HERRAMIENTAS_DE_PERCUSION("Herramientas de Percusión"),
    CERRAJERIA("Cerrajería"),
    ALBAÑILERIA("Albañilería"),
    CARPINTERIA("Carpintería"),
    FONTANERIA_Y_CALEFACCION("Fontanería y Calefacción"),
    PROTECCION_PERSONAL("Protección Personal"),
    HERRAMIENTAS_ESPECIALIZADAS("Herramientas Especializadas"),
    HERRAMIENTAS_MECANICAS("Herramientas Mecánicas"),
    HERRAMIENTAS_AGRICOLAS("Herramientas Agrícolas"),
    HERRAMIENTAS_DE_JARDINERIA("Herramientas de Jardinería"),
    HERRAMIENTAS_PARA_VEHICULOS("Herramientas para Vehículos");

    private final String displayName;

    CategoriaEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getNombreLegible() {
        return displayName;
    }
}
