package com.bank.service;

import com.bank.model.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    boolean createAccount(String documentId);
    Account getAccount(String documentId);
}
