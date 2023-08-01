package com.bank.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.Date;

public class CustomerUtil {
    private static Logger logger = LogManager.getLogger(CustomerUtil.class);

    /**
     * This method checks whether customer lives in a country which is allowed to use bank.
     */
    public static boolean checkCustomerCountry(String[] allowedCountries, String country) {

        for (String allowedCountry : allowedCountries) {
            if (allowedCountry.equalsIgnoreCase(country)) {
                return true;
            }
        }
        logger.info("Country :" + country + "is not in allowed list");

        return false;
    }

    /**
     * This method checks whether customer is younger than 18 years old
     */
    public static boolean checkCustomerAge(Date birthDate){
        Calendar current = Calendar.getInstance();
        current.add(Calendar.YEAR, -18);

        if (current.getTime().before(birthDate)) {
            return false;
        }

        return true;
    }

    /**
     * This method should check whether otp is valid
     */
    public static boolean checkOTPValid(String otp){
        return true;
    }
}
