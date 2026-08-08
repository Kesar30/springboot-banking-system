package org.example.bankingsystem.controller;

import jakarta.validation.Valid;
import org.example.bankingsystem.entities.Transaction;
import org.example.bankingsystem.requests.DepositRequest;
import org.example.bankingsystem.requests.TransferRequest;
import org.example.bankingsystem.requests.WithdrawRequest;
import org.example.bankingsystem.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transactions/withdraw")
    public ResponseEntity<Transaction> withdraw(@Valid @RequestBody WithdrawRequest request){
        try{
//            BigDecimal amount = request.getAmount();
//            if(amount <= 0){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//            }
            Transaction t = transactionService.withdraw(request);
            if(t == null){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            return ResponseEntity.ok(t);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/transactions/deposit")
    public ResponseEntity<Transaction> deposit(@Valid @RequestBody DepositRequest request){
        try{
//            int current_b = transactionService.checkbalance(transaction);
//            BigDecimal amount = request.getAmount();
//            if(amount <= 0){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//            }
            Transaction t = transactionService.deposit(request);
            if(t == null){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

            return ResponseEntity.ok(t);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/transactions/transfer")
    public ResponseEntity<Transaction> transfer(@Valid @RequestBody TransferRequest request){
        try{
//            BigDecimal amount = request.getAmount();
//            if(amount <= 0){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//            }
            Transaction t = transactionService.transferMoney(request);
            if(t == null){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            return ResponseEntity.ok(t);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/user/transactions/{no}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable("no") long accountNo){
        try{
            List<Transaction> transactions = transactionService.showTransactions(accountNo);
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
}
