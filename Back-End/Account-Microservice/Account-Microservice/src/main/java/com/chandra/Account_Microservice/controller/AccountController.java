package com.chandra.Account_Microservice.controller;

import com.chandra.Account_Microservice.entity.*;
import com.chandra.Account_Microservice.serviceImpl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")

public class AccountController {

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @PostMapping("/createAccount/{customerId}")
    public AccountCreationStatus createAccount(@PathVariable("customerId") Long id) {
        return accountServiceImpl.createAccount(id);
    }

    @GetMapping("/getCustomerAccounts/{customerId}")
    public List<Account> getCustomerAccounts(@PathVariable("customerId") Long id) {
        return accountServiceImpl.getCustomerAccounts(id);
    }

    @GetMapping("/getAccount/{accountId}")
    public Optional<Account> getAccount(@PathVariable("accountId") Long id) {
        return accountServiceImpl.getAccount(id);
    }

    @GetMapping("/getAccountStatement/{accountId}/{fromDate}/{toDate}")
    public List<Statement> getAccountStatement(@PathVariable("accountId") Long accountId, @PathVariable("fromDate") LocalDate fromDate, @PathVariable("toDate") LocalDate toDate) {
        return accountServiceImpl.getAccountStatement(accountId, fromDate, toDate);
    }

    @PostMapping("/deposit")
    public TransactionStatus deposit(@RequestBody TransactionRequest depositRequest) {

        return accountServiceImpl.deposit(depositRequest);
    }

    @PostMapping("/withdraw")
    public TransactionStatus withdraw(@RequestBody TransactionRequest withdrawRequest){
        return accountServiceImpl.withdraw(withdrawRequest);
    }

    @PostMapping("/deductServiceCharges")
    public Double serviceChargesDeduction(@RequestBody Account account){
        return accountServiceImpl.deductServiceCharges(account);
    }

}
