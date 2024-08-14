package com.yiyostore.yiyostore_administracion_negocio.model;

/**
 * Enumeración que representa los distintos lugares de compra disponibles en el
 * sistema. Indica el origen desde el cual un cliente realiza una compra.
 */
public enum LugarCompra {

    /**
     * Compra realizada a través de la página de Facebook de la tienda.
     */
    PAGINA_FACEBOOK,
    /**
     * Compra realizada a través de Facebook Marketplace.
     */
    FACEBOOK_MARKETPLACE,
    /**
     * Compra realizada directamente en el local físico de la tienda.
     */
    LOCAL,
    /**
     * Compra realizada por un cliente antiguo, generalmente alguien que ha
     * comprado antes.
     */
    CLIENTES_ANTIGUOS,
    /**
     * Compra realizada por algún otro canal no especificado en los otros
     * valores.
     */
    OTRO
}
