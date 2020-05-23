package com.iac.webshop.helpers;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

public class Utils {

    public static String hashPassword (String password) {

        char[] chars = password.toCharArray();
        byte[] bytes = "webshop-salt-random-A3Xi*bAD".getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, 65536, 512);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(securePassword);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            return null;
        } finally {
            spec.clearPassword();
        }
    }

    public static Boolean isValidEmail(String email) {
        return email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
    }
}
