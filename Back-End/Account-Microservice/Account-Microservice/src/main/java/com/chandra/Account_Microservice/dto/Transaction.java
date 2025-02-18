package com.chandra.Account_Microservice.dto;



import java.time.LocalDate;


public class Transaction {

    private Long id;
    private Long refNo;
    private Long accountId;
    private LocalDate dateOfTransaction;
    private Double withdraw;
    private Double deposit;
    private Double closingBalance;
    private String narration;

    public Transaction(){

    }

    public Transaction(Long id, Long refNo, Long accountId, LocalDate dateOfTransaction, Double withdraw, Double deposit, Double closingBalance, String narration) {
        this.id = id;
        this.refNo = refNo;
        this.accountId = accountId;
        this.dateOfTransaction = dateOfTransaction;
        this.withdraw = withdraw;
        this.deposit = deposit;
        this.closingBalance = closingBalance;
        this.narration = narration;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRefNo() {
        return refNo;
    }

    public void setRefNo(Long refNo) {
        this.refNo = refNo;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public LocalDate getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(LocalDate dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
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

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", refNo=" + refNo +
                ", accountId=" + accountId +
                ", dateOfTransaction=" + dateOfTransaction +
                ", withdraw=" + withdraw +
                ", deposit=" + deposit +
                ", closingBalance=" + closingBalance +
                ", narration='" + narration + '\'' +
                '}';
    }
}
