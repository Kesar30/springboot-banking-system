package org.example.bankingsystem.controller;

import org.example.bankingsystem.entities.Account;
import org.example.bankingsystem.entities.Transaction;
import org.example.bankingsystem.entities.User;
import org.example.bankingsystem.enums.TransactionType;
import org.example.bankingsystem.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> GetAllUsers(){
        try {
            return ResponseEntity.ok(adminService.AllUsers());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("admin/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer uid){
        try {
            User user = adminService.searchUser(uid);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(user);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @PutMapping("/admin/users/{id}/block")
    public ResponseEntity<User> block(@PathVariable("id") Integer uid){
        try {
            User user = adminService.blockUser(uid);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(user);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @PutMapping("/admin/users/{id}/unblock")
    public ResponseEntity<User> unblock(@PathVariable("id") Integer uid){
        try {
            User user = adminService.unblockUser(uid);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(user);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/admin/accounts")
    public ResponseEntity<List<Account>> getAllAccounts(){
        try {
            return ResponseEntity.ok(adminService.allAccounts());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/admin/accounts/{no}")
    public ResponseEntity<Account> getAccount(@PathVariable("no") Long ano){
        try {
            Account account = adminService.searchAccount(ano);
            if (account == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(account);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/admin/accounts/{no}/balance")
    public ResponseEntity<BigDecimal> balance(@PathVariable("no") long number){
        try{
            BigDecimal balance = adminService.showBalance(number);
            if (balance == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(balance);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/admin/accounts/{no}/freeze")
    public ResponseEntity<Account> freeze(@PathVariable("no") Long accountNumber){
        try {
            Account account = adminService.FreezeAccount(accountNumber);
            if (account == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(account);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @PutMapping("/admin/accounts/{no}/close")
    public ResponseEntity<Account> closed(@PathVariable("no") Long accountNumber){
        try {
            Account account = adminService.ClosedAccount(accountNumber);
            if (account == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(account);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @PutMapping("/admin/accounts/{no}/unfreeze")
    public ResponseEntity<Account> block(@PathVariable("no") Long accountNumber){
        try {
            Account account = adminService.UnFreezeAccount(accountNumber);
            if (account == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(account);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/admin/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        try {
            return ResponseEntity.ok(adminService.allTransactions());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/admin/transactions/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable("id") Integer tid){
        try {
            Transaction transaction = adminService.searchTransactionById(tid);
            if (transaction == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(transaction);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/admin/accounts/{accountNo}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable("no") long accountNo){
        try{
            List<Transaction> transactions = adminService.showTransactionsByAccountNo(accountNo);
            if(transactions == null){
                return ResponseEntity.notFound().build();
            }
            if(transactions.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/admin/transactions/date/{date}")
    public ResponseEntity<List<Transaction>> getTransactionFromDate(@PathVariable("date") LocalDate localDateTime){
        try {
            List<Transaction> transactions = adminService.showTransactionsByDate(localDateTime);
            if (transactions.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(transactions);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/admin/transactions/type/{type}")
    public ResponseEntity<List<Transaction>> getTransactionFromType(@PathVariable("type")TransactionType transactionType){
        try {
            List<Transaction> transactions = adminService.showTransactionsByType(transactionType);
            if (transactions.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(transactions);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
