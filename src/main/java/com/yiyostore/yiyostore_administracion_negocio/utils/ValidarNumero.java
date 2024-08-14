package com.yiyostore.yiyostore_administracion_negocio.utils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public class ValidarNumero {

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
}
