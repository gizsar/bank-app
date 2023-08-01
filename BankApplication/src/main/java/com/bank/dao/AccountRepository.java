package com.bank.dao;

import com.bank.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    Account findByCustomerId(String customerId);
    long countByIban(String iban);
}
