package org.example.bankingsystem.repository;

import org.example.bankingsystem.entities.Account;
import org.example.bankingsystem.entities.Transaction;
import org.example.bankingsystem.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction , Integer> {
List<Transaction> findBySenderAccountOrReceiverAccount(Account senderAccount, Account receiverAccount);
    List<Transaction> findByTransactionDateBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    List<Transaction> findByTransactionType(TransactionType transactionType);
}
