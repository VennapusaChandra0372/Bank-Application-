package com.chandra.Transaction_Microservice.dto;



import java.time.LocalDate;


public class Account {

    private Long accountId;
    private Long customerId;
    private String accountType;
    private Double balance;
    private LocalDate lastCheckDate;

    public Account() {

    }

    public Account(Long accountId, Long customerId, String accountType, Double balance, LocalDate lastCheckDate) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = balance;
        this.lastCheckDate = lastCheckDate;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LocalDate getLastCheckDate() {
        return lastCheckDate;
    }

    public void setLastCheckDate(LocalDate lastCheckDate) {
        this.lastCheckDate = lastCheckDate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", customerId=" + customerId +
                ", accountType='" + accountType + '\'' +
                ", balance='" + balance + '\'' +
                ", lastCheckDate=" + lastCheckDate +
                '}';
    }
}
