package com.bank.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestCustomerUtil {

    @Test
    public void test_check_age() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String dateInString = "7-5-1990";
        Date date = formatter.parse(dateInString);
        boolean validAge = CustomerUtil.checkCustomerAge(date);

        assertEquals("Passed", true, validAge);
    }

    @Test
    public void test_check_age_invalid() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String dateInString = "7-5-2020";
        Date date = formatter.parse(dateInString);
        boolean validAge = CustomerUtil.checkCustomerAge(date);

        assertEquals("Passed", false, validAge);
    }

    @Test
    public void test_check_county() {
        String[] allowedCountries = {"Netherlands", "Belgium"};
        boolean validCountry = CustomerUtil.checkCustomerCountry(allowedCountries, "Netherlands");

        assertEquals("Passed", true, validCountry);
    }

    @Test
    public void test_check_county_not_allowed() {
        String[] allowedCountries = {"Netherlands", "Belgium"};
        boolean validCountry = CustomerUtil.checkCustomerCountry(allowedCountries, "France");

        assertEquals("Passed", false, validCountry);
    }
}
