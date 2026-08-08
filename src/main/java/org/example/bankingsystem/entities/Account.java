package org.example.bankingsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.example.bankingsystem.enums.AccountStatus;
import org.example.bankingsystem.enums.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountNo;   // ✅ Primary key
    @Enumerated(EnumType.STRING)
    private AccountType accountType;   // ✅ Fixed typo

    private BigDecimal balance = BigDecimal.ZERO;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")   // ✅ Foreign key column
    @JsonBackReference
    private User user;
    @PrePersist
    public void onCreate(){
        createdAt = LocalDateTime.now();
        status = AccountStatus.ACTIVE;
    }

    // constructors, getters, setters

    public Account() {
    }

    public Account(Long accountNo, AccountType accountType, BigDecimal balance, User user, List<Transaction> transactions) {
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.balance = balance;
        this.user = user;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
