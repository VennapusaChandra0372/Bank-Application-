package com.chandra.Account_Microservice.entity;

import jakarta.persistence.Entity;


public class AccountCreationStatus {

    private String message;
    private Long accountId;

    public AccountCreationStatus(){

    }

    public AccountCreationStatus(String message, Long accountId) {
        this.message = message;
        this.accountId = accountId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "AccountCreationStatus{" +
                "message='" + message + '\'' +
                ", accountId=" + accountId +
                '}';
    }
}
