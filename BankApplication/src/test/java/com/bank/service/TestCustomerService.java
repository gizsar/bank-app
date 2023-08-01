package com.bank.service;

import com.bank.dao.CustomerRepository;
import com.bank.model.Address;
import com.bank.model.Customer;
import com.bank.service.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@DataJpaTest
public class TestCustomerService {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_save() {
        Address address = new Address("Hoogstraat", "Eindhoven", "1111AB", "Netherlands");
        Customer customer = new Customer("Gizem", address, null, "123", "AB123456", "gizems");
        String password = customerService.createCustomer(customer);
        assertNotEquals("Passed", "", password);
    }

    @Test
    public void test_create_token_empty() {
        String token = customerService.createToken("username", "password");
        assertEquals("Passed", "", token);

    }

    @Test
    public void test_login_with_invalid_token() {
        Optional<User> user = customerService.login("token");
        assertEquals("Passed", false, user.isPresent());
    }
}
