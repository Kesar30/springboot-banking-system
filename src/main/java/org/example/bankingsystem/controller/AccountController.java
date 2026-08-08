package org.example.bankingsystem.controller;

import jakarta.validation.Valid;
import org.example.bankingsystem.requests.AccountRequest;
import org.example.bankingsystem.services.AccountService;
import org.example.bankingsystem.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/accounts")
    public ResponseEntity<Account> create(@Valid @RequestBody AccountRequest request){
        try {
            Account account =  accountService.createAccount(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(account);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/balance/{no}")
    public ResponseEntity<BigDecimal> balance(@PathVariable("no") long number){
        try{
            BigDecimal balance = accountService.showBalance(number);
            if (balance == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(balance);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/accounts/user/{id}")
    public ResponseEntity<List<Account>> show(@PathVariable("id") int aid){
        try{
            List<Account> accounts = accountService.showAccounts(aid);
            if(accounts.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(accounts);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("account/{no}")
    public ResponseEntity<Void> delete(@PathVariable("no") long accountNo){
       try{
           accountService.deleteAccount(accountNo);
           return ResponseEntity.noContent().build();
       } catch (Exception e) {
           return ResponseEntity.internalServerError().build();
       }
    }


}
