package org.example.bankingsystem.services;

import org.example.bankingsystem.entities.Account;
import org.example.bankingsystem.entities.User;
import org.example.bankingsystem.repository.AccountRepository;
import org.example.bankingsystem.repository.UserRepository;
import org.example.bankingsystem.requests.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    //method to create account number
    public Account createAccount(AccountRequest request){
        Account account = new Account();
        account.setAccountType(request.getAccountType());
//        account.setBalance(request.getBalance());
        account.setUser(request.getUser());
        return accountRepository.save(account);
    }
    //getting balance of the account
    public BigDecimal showBalance(long accNo){
        Optional<Account> account = accountRepository.findById(accNo);
        if(account.isPresent()){
            Account a = account.get();
            return a.getBalance();
        }
        return null;
    }
    //getting one or more account number of user
    public List<Account> showAccounts(Integer userId) {
        if (!userRepository.existsById(userId)) {
            return null;
        }
        return accountRepository.findByUserUserId(userId);
    }
    public void deleteAccount(long accountNo){
        accountRepository.deleteById(accountNo);
    }

}
