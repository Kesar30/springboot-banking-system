package org.example.bankingsystem.services;

import jakarta.transaction.Transactional;
import org.example.bankingsystem.entities.Account;
import org.example.bankingsystem.entities.Transaction;
import org.example.bankingsystem.enums.TransactionStatus;
import org.example.bankingsystem.enums.TransactionType;
import org.example.bankingsystem.requests.DepositRequest;
import org.example.bankingsystem.requests.TransferRequest;
import org.example.bankingsystem.repository.AccountRepository;
import org.example.bankingsystem.repository.TransactionRepository;
import org.example.bankingsystem.requests.WithdrawRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;


    //withdraw money
    public Transaction withdraw(WithdrawRequest request){
        Optional<Account> account = accountRepository.findById(request.getAccountNo());
        if(account.isEmpty()){
            return null;
        }
        Account a = account.get();
        BigDecimal balance = a.getBalance();
        BigDecimal amount = request.getAmount();
        //balance < amount
        if(balance.compareTo(amount) < 0){
            return null;
        }
        BigDecimal newBalance = balance.subtract(amount);
        a.setBalance(newBalance);
        accountRepository.save(a);
        Transaction transaction = new Transaction();
        transaction.setSenderAccount(a);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.WITHDRAW);

        return transactionRepository.save(transaction);
    }

    //deposit money
    public Transaction deposit(DepositRequest request){
        Optional<Account> account = accountRepository.findById(request.getAccountNo());
        if(account.isEmpty()){
            return null;
        }
        Account a = account.get();
        BigDecimal balance = a.getBalance();
        BigDecimal amount = request.getAmount();

        BigDecimal newBalance = balance.add(amount);
        a.setBalance(newBalance);
        accountRepository.save(a);
        Transaction transaction = new Transaction();
        transaction.setReceiverAccount(a);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        return transactionRepository.save(transaction);
    }
    //transfer money
    @Transactional
    public Transaction transferMoney(TransferRequest request) {

        Optional<Account> account1 = accountRepository.findById(request.getSenderAccountNo());
        Optional<Account> account2 = accountRepository.findById(request.getReceiverAccountNo());
        if (account1.isEmpty() || account2.isEmpty()) {
            return null;
        }
        Account sender = account1.get();
        Account receiver = account2.get();
        BigDecimal senderBalance = sender.getBalance();
        BigDecimal receiverBalance = receiver.getBalance();
        BigDecimal amount = request.getAmount();
        if (senderBalance.compareTo(amount) < 0) {
            return null;
        }
        BigDecimal new_senderB = senderBalance.subtract(amount);
        BigDecimal new_receiverB = receiverBalance.add(amount);
        sender.setBalance(new_senderB);
        receiver.setBalance(new_receiverB);
        accountRepository.save(sender);
        accountRepository.save(receiver);

        Transaction transaction = new Transaction();

        transaction.setSenderAccount(sender);
        transaction.setReceiverAccount(receiver);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setStatus(TransactionStatus.SUCCESS);

        return transactionRepository.save(transaction);

    }
    public List<Transaction> showTransactions(Long accountNo){
        Optional<Account> account = accountRepository.findById(accountNo);
        if(account.isPresent()){
            Account a = account.get();
            List<Transaction> transactionList = transactionRepository.findBySenderAccountOrReceiverAccount(a , a);
            return transactionList;
        }
        return null;
    }
}
