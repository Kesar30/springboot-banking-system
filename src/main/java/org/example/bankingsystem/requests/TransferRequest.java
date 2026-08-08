package org.example.bankingsystem.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class TransferRequest {
    @NotNull(message = "Account Number is required")
    private Long senderAccountNo;

    @NotNull(message = "Account Number is required")
    private Long receiverAccountNo;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount should be greater than zero")
    private BigDecimal amount;

    public TransferRequest(Long senderAccountNo, Long receiverAccountNo, BigDecimal amount) {
        this.senderAccountNo = senderAccountNo;
        this.receiverAccountNo = receiverAccountNo;
        this.amount = amount;
    }

    public TransferRequest() {
    }

    public Long getSenderAccountNo() {
        return senderAccountNo;
    }

    public void setSenderAccountNo(Long senderAccountNo) {
        this.senderAccountNo = senderAccountNo;
    }

    public Long getReceiverAccountNo() {
        return receiverAccountNo;
    }

    public void setReceiverAccountNo(Long receiverAccountNo) {
        this.receiverAccountNo = receiverAccountNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
