package org.example.bankingsystem.services;

import org.example.bankingsystem.entities.Account;
import org.example.bankingsystem.entities.User;
import org.example.bankingsystem.exceptions.AccountNotFoundException;
import org.example.bankingsystem.exceptions.UserNotFoundException;
import org.example.bankingsystem.repository.AccountRepository;
import org.example.bankingsystem.repository.UserRepository;
import org.example.bankingsystem.requests.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Account createAccount(AccountRequest request) {

        Account account = new Account();
        account.setAccountType(request.getAccountType());
        User user = userRepository.findById(request.getUser().getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with Id : " + request.getUser().getUserId()));

//        Optional<User> optionalUser =
//                userRepository.findById(request.getUser().getUserId());
//
//        if (optionalUser.isEmpty()) {
//            throw new UserNotFoundException(
//                    "User not found with Id : " + request.getUser().getUserId());
//        }
//        User user = optionalUser.get();
        account.setUser(user);

        return accountRepository.save(account);
    }
    //getting balance of the account
    public BigDecimal showBalance(long accNo){
        Optional<Account> account = accountRepository.findById(accNo);
        if(account.isPresent()){
            Account a = account.get();
            return a.getBalance();
        }
        throw new AccountNotFoundException("Account not found with account number : "+accNo);
    }
    //getting one or more account number of user
    public List<Account> showAccounts(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with Id : "+userId);
        }
        List<Account> accounts = accountRepository.findByUserUserId(userId);
        if (accounts.isEmpty()){
            throw new AccountNotFoundException("No Accounts found for User id : "+userId);
        }
        return accounts;
    }
    public void deleteAccount(long accountNo){
        if (!accountRepository.existsById(accountNo)) {
            throw new AccountNotFoundException("Account not found with account number : "+accountNo);

        }
        accountRepository.deleteById(accountNo);
    }

}
