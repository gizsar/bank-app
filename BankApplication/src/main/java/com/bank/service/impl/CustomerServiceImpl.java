package com.bank.service.impl;

import com.bank.dao.CustomerRepository;
import com.bank.model.Customer;
import com.bank.service.CustomerService;
import com.bank.util.PasswordUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    private Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

    public String createCustomer(Customer customer) {
        try {
            String[] passwordArr = PasswordUtil.generateEncryptedPassword();
            customer.setPassword(passwordArr[1]);
            customerRepository.save(customer);
            return passwordArr[0];
        } catch (Exception e) {
            logger.error(e);
            return "";
        }
    }

    public String createToken(String username, String password) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer != null && PasswordUtil.checkPassword(password, customer.getPassword())) {
            String token = UUID.randomUUID().toString();
            customer.setToken(token);
            customerRepository.save(customer);
            return token;

        }
        return "";
    }

    public Optional<User> login(String token) {
        Optional<Customer> optional = customerRepository.findByToken(token);
        if(optional.isPresent()){
            Customer customer = optional.get();
            User user= new User(customer.getUsername(), customer.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return  Optional.empty();
    }

}
