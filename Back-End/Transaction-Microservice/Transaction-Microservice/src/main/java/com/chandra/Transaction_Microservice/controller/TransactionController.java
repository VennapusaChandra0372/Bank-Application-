package com.chandra.Transaction_Microservice.controller;

import com.chandra.Transaction_Microservice.entity.Transaction;
import com.chandra.Transaction_Microservice.entity.TransactionRequest;
import com.chandra.Transaction_Microservice.entity.TransactionStatus;
import com.chandra.Transaction_Microservice.entity.TransferRequest;
import com.chandra.Transaction_Microservice.serviceImpl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {


    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @PostMapping("/deposit")
    public TransactionStatus deposit(@RequestBody TransactionRequest request){
        return transactionServiceImpl.deposit(request);
    }

    @PostMapping("/withdraw")
    public TransactionStatus withdraw(@RequestBody TransactionRequest request){
        return transactionServiceImpl.withdraw(request);
    }

    @PostMapping("/transfer")
    public TransactionStatus transferAmount(@RequestBody TransferRequest request){
        return transactionServiceImpl.transferAmount(request);
    }

    @GetMapping("/getTransactions/{customerId}")
    public List<Transaction> getTransactions(@PathVariable("customerId") Long customerId){
        return transactionServiceImpl.getTransactions(customerId);
    }

    @GetMapping("/getTransactionsByAccountId/{accountId}")
    public List<Transaction> getTransactionsByAccountId(@PathVariable("accountId") Long accountId){
        return transactionServiceImpl.getTransactionsByAccountId(accountId);
    }
}
