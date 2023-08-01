package com.bank.service.impl;

import com.bank.dao.AccountRepository;
import com.bank.model.Account;
import com.bank.model.EnumAccountType;
import com.bank.service.AccountService;
import com.bank.util.AccountUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    private Logger logger = LogManager.getLogger(AccountServiceImpl.class);

    public boolean createAccount(String customerId) {
        try {
            Account account = new Account(customerId, 0d, getIbanUnigue(), EnumAccountType.NORMAL, "EURO");
            accountRepository.save(account);
            return true;
        } catch (Exception e) {
            logger.error("Error was occurred while creating an account", e);
            return false;
        }
    }

    @Override
    public Account getAccount(String customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    private String getIbanUnigue() {
        long count = 0;
        String iban = "";
        do {
            iban = AccountUtil.generateIBAN();
            count = accountRepository.countByIban(iban);
        } while (count != 0);

        return iban;
    }
}
