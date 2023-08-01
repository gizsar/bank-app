package com.bank.model;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "iban")
})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String customerId;

    private Double balance;
    @Column(nullable = false)
    private String iban;
    private EnumAccountType accountType;
    private String currency;

    public Account() {
    }

    public Account(String customerId, Double balance, String iban, EnumAccountType accountType, String currency) {
        this.customerId = customerId;
        this.balance = balance;
        this.iban = iban;
        this.accountType = accountType;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public EnumAccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(EnumAccountType accountType) {
        this.accountType = accountType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", balance=" + balance +
                ", iban='" + iban + '\'' +
                ", accountType=" + accountType +
                ", currency='" + currency + '\'' +
                '}';
    }
}
