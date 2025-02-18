package com.chandra.Transaction_Microservice.entity;

public class TransactionRequest {

    private Long accountId;
    private Double amount;

    public TransactionRequest(){

    }

    public TransactionRequest(Long accountId, Double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "accountId=" + accountId +
                ", amount=" + amount +
                '}';
    }
}
