package com.bank.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;

public class PasswordUtil {

    /**
     * Generate encrypt password.
     *
     * @return generated encrypted password
     */
    public static String[] generateEncryptedPassword() {
        String password = generatePassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        String[] passwordArr = {password, encodedPassword};
        return passwordArr;
    }

    /**
     * Generate random password and encrypt it.
     *
     * @return generated encrypted password
     */
    private static String generatePassword() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }


    /**
     * Encrypt given password
     *
     * @return encrypted password
     */
    public static boolean checkPassword(String password, String encodedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, encodedPassword);
    }

}
