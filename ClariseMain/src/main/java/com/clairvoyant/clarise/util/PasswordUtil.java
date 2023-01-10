package com.clairvoyant.clarise.util;

import java.util.Base64;

public class PasswordUtil {
    public static String encode(String password) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(password.getBytes());
    }

    public static String decode(String encryptedPassword) {
        Base64.Decoder decoder = Base64.getDecoder();
        String password = new String(decoder.decode(encryptedPassword));
        return password;
    }
}
