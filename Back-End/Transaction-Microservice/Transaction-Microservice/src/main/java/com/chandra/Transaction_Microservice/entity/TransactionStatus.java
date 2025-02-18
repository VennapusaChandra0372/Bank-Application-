package com.chandra.Transaction_Microservice.entity;

public class TransactionStatus {

    private String message;
    private Double sourceBalance;
    private Double destinationBalance;

    public TransactionStatus(){

    }

    public TransactionStatus(String message, Double sourceBalance, Double destinationBalance) {
        this.message = message;
        this.sourceBalance = sourceBalance;
        this.destinationBalance = destinationBalance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getSourceBalance() {
        return sourceBalance;
    }

    public void setSourceBalance(Double sourceBalance) {
        this.sourceBalance = sourceBalance;
    }

    public Double getDestinationBalance() {
        return destinationBalance;
    }

    public void setDestinationBalance(Double destinationBalance) {
        this.destinationBalance = destinationBalance;
    }

    @Override
    public String toString() {
        return "TransactionStatus{" +
                "message='" + message + '\'' +
                ", sourceBalance=" + sourceBalance +
                ", destinationBalance=" + destinationBalance +
                '}';
    }
}
