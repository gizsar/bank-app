package com.bank.controller;

import com.bank.model.Account;
import com.bank.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AccountController{

    @Autowired
    private AccountService accountService;
    private Logger logger = LogManager.getLogger(AccountController.class);

    @GetMapping(value = "/overview")
    public ResponseEntity<Account> overviewAccount(@RequestParam("documentId") String documentId) throws JsonProcessingException, IOException {
        Account customerAccount = accountService.getAccount(documentId);

        if( customerAccount == null){
            logger.info("Customer account is not found. Check customer id");
            return new ResponseEntity("Customer account is not found.",HttpStatus.NOT_FOUND);

        }
        return ResponseEntity.ok(customerAccount);
    }
}
