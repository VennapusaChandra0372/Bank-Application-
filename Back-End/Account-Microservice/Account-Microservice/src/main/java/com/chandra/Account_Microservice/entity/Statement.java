package com.chandra.Account_Microservice.entity;

import jakarta.persistence.Entity;

import java.time.LocalDate;


public class Statement {

    private LocalDate date;
    private Long refNo;
    private Double withdraw;
    private Double deposit;
    private Double closingBalance;
    private String description;

    public Statement (){

    }

    public Statement(LocalDate date, Long refNo, Double withdraw, Double deposit, Double closingBalance, String description) {
        this.date = date;
        this.refNo = refNo;
        this.withdraw = withdraw;
        this.deposit = deposit;
        this.closingBalance = closingBalance;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getRefNo() {
        return refNo;
    }

    public void setRefNo(Long refNo) {
        this.refNo = refNo;
    }

    public Double getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Double withdraw) {
        this.withdraw = withdraw;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(Double closingBalance) {
        this.closingBalance = closingBalance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Statement{" +
                "date=" + date +
                ", refNo=" + refNo +
                ", withdraw=" + withdraw +
                ", deposit=" + deposit +
                ", closingBalance=" + closingBalance +
                ", description='" + description + '\'' +
                '}';
    }
}
