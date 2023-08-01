package com.bank.service;

import com.bank.model.Customer;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface CustomerService {

    String createCustomer(Customer customer);
    Optional<User> login(String token);
    String createToken(String username, String password);
}
