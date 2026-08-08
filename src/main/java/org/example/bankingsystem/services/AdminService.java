package org.example.bankingsystem.services;

import org.example.bankingsystem.entities.Account;
import org.example.bankingsystem.entities.Transaction;
import org.example.bankingsystem.entities.User;
import org.example.bankingsystem.enums.AccountStatus;
import org.example.bankingsystem.enums.TransactionType;
import org.example.bankingsystem.enums.UserStatus;
import org.example.bankingsystem.repository.AccountRepository;
import org.example.bankingsystem.repository.TransactionRepository;
import org.example.bankingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;



    public List<User> AllUsers(){
        List <User> users = userRepository.findAll();
        return users;
    }

    public User searchUser(Integer id){
        Optional<User> user =  userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    public User blockUser(Integer id){
        Optional<User> optional =  userRepository.findById(id);
        if(optional.isPresent()){
            User user =  optional.get();
            user.setStatus(UserStatus.BLOCKED);
            return userRepository.save(user);
        }
        return null;
    }

    public User unblockUser(Integer id){
        Optional<User> optional =  userRepository.findById(id);
        if(optional.isPresent()){
            User user =  optional.get();
            user.setStatus(UserStatus.ACTIVE);
            return userRepository.save(user);
        }
        return null;
    }

    public List<Account> allAccounts(){
        List<Account> accounts =  accountRepository.findAll();
        return accounts;
    }

    public Account searchAccount(Long no){
        Optional<Account> optional = accountRepository.findById(no);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public BigDecimal showBalance(long accNo){
        Optional<Account> account = accountRepository.findById(accNo);
        if(account.isPresent()){
            Account a = account.get();
            return a.getBalance();
        }
        return null;
    }

    public Account FreezeAccount(Long no){
        Optional<Account> optional =  accountRepository.findById(no);
        if(optional.isPresent()){
            Account account =  optional.get();
            account.setStatus(AccountStatus.FROZEN);
            return accountRepository.save(account);
        }
        return null;
    }

    public Account ClosedAccount(Long no){
        Optional<Account> optional =  accountRepository.findById(no);
        if(optional.isPresent()){
            Account account =  optional.get();
            account.setStatus(AccountStatus.CLOSED);
            return accountRepository.save(account);
        }
        return null;
    }

    public Account UnFreezeAccount(Long no){
        Optional<Account> optional =  accountRepository.findById(no);
        if(optional.isPresent()){
            Account account =  optional.get();
            account.setStatus(AccountStatus.ACTIVE);
            return accountRepository.save(account);
        }
        return null;
    }

    public List<Transaction> allTransactions(){
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions;
    }

    public Transaction searchTransactionById(Integer id){
        Optional<Transaction> optional = transactionRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public List<Transaction> showTransactionsByAccountNo(Long accountNo){
        Optional<Account> account = accountRepository.findById(accountNo);
        if(account.isPresent()){
            Account a = account.get();
            List<Transaction> transactionList = transactionRepository.findBySenderAccountOrReceiverAccount(a , a);
            return transactionList;
        }
        return null;
    }

    public List<Transaction> showTransactionsByDate(LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();

        return transactionRepository
                .findByTransactionDateBetween(start, end);
    }

    public List<Transaction> showTransactionsByType(TransactionType transactionType){
        List<Transaction> transactions = transactionRepository.findByTransactionType(transactionType);
        return transactions;
    }

}
