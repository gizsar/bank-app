package com.bank.util;

import java.util.Random;

public class AccountUtil {

    /**
    * Generate IBAN according to format NL-99-XYZ-123456789
    */
    public static String generateIBAN(){
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        builder.append("NL")
                .append(random.nextInt(10))
                .append(random.nextInt(10))
                .append("XYZ")
                .append(random.nextInt(10))
                .append(random.nextInt(10))
                .append(random.nextInt(10))
                .append(random.nextInt(10))
                .append(random.nextInt(10))
                .append(random.nextInt(10))
                .append(random.nextInt(10))
                .append(random.nextInt(10))
                .append(random.nextInt(10));
        return builder.toString();
    }
}
