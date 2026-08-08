package org.example.bankingsystem.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class DepositRequest {

    @NotNull(message = "Account Number is required")
    private Long accountNo;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount should be greater than zero")
    private BigDecimal amount;

    public DepositRequest() {
    }

    public DepositRequest(Long accountNo, BigDecimal amount) {
        this.accountNo = accountNo;
        this.amount = amount;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
