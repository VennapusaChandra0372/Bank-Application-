package com.chandra.Transaction_Microservice.service;

import com.chandra.Transaction_Microservice.entity.Transaction;
import com.chandra.Transaction_Microservice.entity.TransactionRequest;
import com.chandra.Transaction_Microservice.entity.TransactionStatus;
import com.chandra.Transaction_Microservice.entity.TransferRequest;

import java.util.List;

public interface TransactionService {

    public Transaction saveTransaction(Transaction transaction);

    public TransactionStatus deposit(TransactionRequest request);
    public TransactionStatus withdraw(TransactionRequest request);
    public TransactionStatus transferAmount(TransferRequest request);

    public List<Transaction> getTransactions(Long customerId);

    public List<Transaction> getTransactionsByAccountId(Long accountId);



}
