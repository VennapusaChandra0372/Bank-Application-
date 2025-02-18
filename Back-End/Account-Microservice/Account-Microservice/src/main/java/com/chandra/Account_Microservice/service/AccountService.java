package com.chandra.Account_Microservice.service;

import com.chandra.Account_Microservice.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AccountService {

    public AccountCreationStatus createAccount(Long customerId);
    public List<Account> getCustomerAccounts(Long customerId);
    public Optional<Account> getAccount(Long accountId);
    public List<Statement> getAccountStatement(Long accountId, LocalDate fromDate, LocalDate toDate);
    public TransactionStatus deposit(TransactionRequest depositRequest);
    public TransactionStatus withdraw(TransactionRequest withdrawRequest);

    public Double deductServiceCharges(Account account);
}
