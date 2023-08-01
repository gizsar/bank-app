package com.bank.controller;

import com.bank.model.Customer;
import com.bank.service.AccountService;
import com.bank.service.CustomerService;
import com.bank.util.CustomerUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {
    @Value("${allowedCountries}")
    private String[] allowedCountries;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    private Logger logger = LogManager.getLogger(CustomerController.class);

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    @Transactional
    public ResponseEntity<String> register(@RequestBody Customer customer, HttpServletRequest request) {

        if (!CustomerUtil.checkCustomerCountry(allowedCountries, customer.getAddress().getCountry())) {
            return new ResponseEntity("Country is not in allowed list", HttpStatus.NOT_ACCEPTABLE);
        }

        if (!CustomerUtil.checkCustomerAge(customer.getBirthDate())) {
            return new ResponseEntity("You are younger than 18 years old.", HttpStatus.NOT_ACCEPTABLE);
        }

        if (!CustomerUtil.checkOTPValid(customer.getOtp())) {
            return new ResponseEntity("OTP is wrong", HttpStatus.NOT_FOUND);
        }

        String defaultPassword = customerService.createCustomer(customer);
        if (!accountService.createAccount(customer.getDocumentId())) {
            logger.error("Customer account couldn't generated");
            return new ResponseEntity("Exception was occurred", HttpStatus.BAD_REQUEST);

        }
        logger.info("Customer was registered successfully");

        return ResponseEntity.ok("Customer was created on system. username:" + customer.getUsername() + " password:" + defaultPassword);

    }

    @PostMapping("/token")
    public String getToken(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        String token = customerService.createToken(username, password);

        return token;
    }


    @GetMapping("/login")
    public ResponseEntity<String> loginSystem(@RequestParam("token") String token) {
        Optional optional = customerService.login(token);
        if (optional.isPresent()) {
            return new ResponseEntity("Login successful", HttpStatus.OK);
        }
        return new ResponseEntity("Invalid token", HttpStatus.NOT_FOUND);
    }
}
