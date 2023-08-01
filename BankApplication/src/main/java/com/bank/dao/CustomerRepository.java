package com.bank.dao;

import com.bank.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findByUsername(String username);
    Optional<Customer> findByToken(String token);
}
