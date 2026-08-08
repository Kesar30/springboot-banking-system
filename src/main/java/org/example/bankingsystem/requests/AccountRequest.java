package org.example.bankingsystem.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.bankingsystem.entities.User;
import org.example.bankingsystem.enums.AccountType;

public class AccountRequest {
    @NotNull(message = "Account type is required")
    private AccountType accountType;

//    private double balance;

    private User user;

    public AccountRequest() {
    }

    public AccountRequest(AccountType accountType, double balance, User user) {
        this.accountType = accountType;
//        this.balance = balance;
        this.user = user;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

//    public double getBalance() {
//        return balance;
//    }
//
//    public void setBalance(double balance) {
//        this.balance = balance;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
