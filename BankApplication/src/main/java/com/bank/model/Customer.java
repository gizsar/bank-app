package com.bank.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username", "documentId"})
})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String documentId;
    @Column(nullable = false)
    private String name;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(nullable = false)
    private Address address;
    @Column(nullable = false)
    private Date birthDate;
    private String otp;
    @Column(nullable = false)
    private String username;

    private String password;

    private String token;

    public Customer(String name, Address address, Date birthDate, String otp, String documentId, String username) {
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.otp = otp;
        this.documentId = documentId;
        this.username = username;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", documentId='" + documentId + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", birthDate=" + birthDate +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
