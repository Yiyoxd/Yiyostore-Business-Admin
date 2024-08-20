package com.yiyostore.yiyostore_administracion_negocio.utils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.yiyostore.yiyostore_administracion_negocio.exception.NumeroTelefonoInvalidoException;

/**
 * Utilidades para la validación y normalización de números de teléfono.
 */
public class TelefonoUtils {

    private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    /**
     * Valida el número de teléfono utilizando la biblioteca libphonenumber.
     *
     * @param numeroTelefono el número de teléfono a validar.
     * @return true si el número de teléfono es válido, false en caso contrario.
     */
    public static boolean esValido(String numeroTelefono) {
        try {
            Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(numeroTelefono, "MX");
            return phoneUtil.isValidNumber(phoneNumber);
        } catch (NumberParseException e) {
            return false;
        }
    }

    /**
     * Normaliza el número de teléfono al formato estándar sin guiones, espacios
     * o códigos de país.
     *
     * @param numeroTelefono el número de teléfono a normalizar.
     * @return el número de teléfono normalizado en el formato "8714140550".
     * @throws NumeroTelefonoInvalidoException si el número de teléfono no es
     * válido o no se puede normalizar.
     */
    public static String normalizar(String numeroTelefono) {
        try {
            Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(numeroTelefono, "MX");
            if (phoneUtil.isValidNumber(phoneNumber)) {
                return String.valueOf(phoneNumber.getNationalNumber());
            }
            throw new NumeroTelefonoInvalidoException(numeroTelefono);
        } catch (NumberParseException e) {
            throw new NumeroTelefonoInvalidoException("Número de teléfono inválido: " + numeroTelefono, e);
        }
    }
}
