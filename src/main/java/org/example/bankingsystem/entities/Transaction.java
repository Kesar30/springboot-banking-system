package org.example.bankingsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.example.bankingsystem.enums.TransactionStatus;
import org.example.bankingsystem.enums.TransactionType;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer transactionId;
//    private long senderAccountNo;
//    private long receiverAccountNo;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private BigDecimal amount;


    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "sender_no")
    private Account senderAccount;
    @ManyToOne
    @JoinColumn(name = "receiver_no")
    private Account receiverAccount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    public Transaction() {
    }

    public Transaction(Integer transactionId, TransactionType transactionType, BigDecimal amount, LocalDateTime transactionDate, Account senderAccount,Account receiverAccount , TransactionStatus status) {
        this.transactionId = transactionId;
//        this.senderAccountNo = senderAccountNo;
//        this.receiverAccountNo = receiverAccountNo;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.status = status;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

//    public long getSenderAccountNo() {
//        return senderAccountNo;
//    }
//
//    public void setSenderAccountNo(long senderAccountNo) {
//        this.senderAccountNo = senderAccountNo;
//    }
//
//    public long getReceiverAccountNo() {
//        return receiverAccountNo;
//    }
//
//    public void setReceiverAccountNo(long receiverAccountNo) {
//        this.receiverAccountNo = receiverAccountNo;
//    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
