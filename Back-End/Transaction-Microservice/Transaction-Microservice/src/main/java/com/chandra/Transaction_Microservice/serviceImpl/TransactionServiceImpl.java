package com.chandra.Transaction_Microservice.serviceImpl;

import com.chandra.Transaction_Microservice.dto.Account;
import com.chandra.Transaction_Microservice.entity.Transaction;
import com.chandra.Transaction_Microservice.entity.TransactionRequest;
import com.chandra.Transaction_Microservice.entity.TransactionStatus;
import com.chandra.Transaction_Microservice.entity.TransferRequest;
import com.chandra.Transaction_Microservice.repository.TransactionRepo;
import com.chandra.Transaction_Microservice.service.TransactionProxy;
import com.chandra.Transaction_Microservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private TransactionProxy transactionProxy;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    @Override
    public TransactionStatus deposit(TransactionRequest request) {
        TransactionStatus status = transactionProxy.deposit(request);

        if (status.getMessage().equals("Account not found..")) {
            return status;
        }

        Transaction transaction = new Transaction();
        Long referenceNo = new Random().nextLong(100000L, 999999L);
        transaction.setRefNo(referenceNo);
        transaction.setAccountId(request.getAccountId());
        transaction.setDateOfTransaction(LocalDate.now());
        transaction.setDeposit(request.getAmount());
        transaction.setWithdraw(0.0);
        transaction.setClosingBalance(status.getSourceBalance());
        transaction.setNarration("self" + request.getAccountId());

        transactionRepo.save(transaction);

        return status;
    }

    @Override
    public TransactionStatus withdraw(TransactionRequest request) {
        TransactionStatus status = transactionProxy.withdraw(request);

        if (status.getMessage().equals("Account not found..") || status.getMessage().equals("Insuffiecient Funds..")) {
            return status;
        }

        Transaction transaction = new Transaction();
        Long referenceNo = new Random().nextLong(100000L, 999999L);
        transaction.setRefNo(referenceNo);
        transaction.setAccountId(request.getAccountId());
        transaction.setDateOfTransaction(LocalDate.now());
        transaction.setDeposit(0.0);
        transaction.setWithdraw(request.getAmount());
        transaction.setClosingBalance(status.getSourceBalance());
        transaction.setNarration("self" + request.getAccountId());

        transactionRepo.save(transaction);

        return status;
    }

    @Override
    @Transactional
    public TransactionStatus transferAmount(TransferRequest request) {
        Optional<Account> sourceAccount = transactionProxy.getAccount(request.getSourceAccountId());
        Optional<Account> targetAccount = transactionProxy.getAccount(request.getTargetAccountId());

        TransactionStatus status = new TransactionStatus();

        if (sourceAccount.isEmpty() && targetAccount.isEmpty()) {
            status.setMessage("Both accounts not exist");
            status.setSourceBalance(0.0);
            status.setDestinationBalance(0.0);
        } else if (sourceAccount.isEmpty()) {
            status.setMessage("source account not exist");
            status.setSourceBalance(0.0);
            status.setDestinationBalance(0.0);
        } else if (targetAccount.isEmpty()) {
            status.setMessage("Traget account not exist");
            status.setSourceBalance(0.0);
            status.setDestinationBalance(0.0);
        } else {
            TransactionRequest tRequest = new TransactionRequest();

            tRequest.setAccountId(request.getSourceAccountId());
            tRequest.setAmount(request.getAmount());

            status = transactionProxy.withdraw(tRequest);

            TransactionRequest dRequest = new TransactionRequest();

            tRequest.setAccountId(request.getTargetAccountId());
            tRequest.setAmount(request.getAmount());

            if (status.getMessage().equals("Account not found..") || status.getMessage().equals("Insuffiecient Funds..")) {
                return status;
            }

            transactionProxy.deposit(dRequest);
            sourceAccount.get().setBalance(sourceAccount.get().getBalance() - request.getAmount());
            targetAccount.get().setBalance(targetAccount.get().getBalance() - request.getAmount());

            status.setMessage("Amount Transferred Succesfully.. to: " + targetAccount.get().getAccountId());
            status.setSourceBalance(sourceAccount.get().getBalance());
            status.setDestinationBalance(targetAccount.get().getBalance());

            Transaction transaction = new Transaction();
            Long referenceNo = new Random().nextLong(100000L, 999999L);
            transaction.setRefNo(referenceNo);
            transaction.setAccountId(request.getTargetAccountId());
            transaction.setDateOfTransaction(LocalDate.now());
            transaction.setDeposit(request.getAmount());
            transaction.setWithdraw(0.0);
            transaction.setClosingBalance(targetAccount.get().getBalance());
            transaction.setNarration("from" + request.getSourceAccountId());

            transactionRepo.save(transaction);

            Transaction destTransaction = new Transaction();

            transaction.setRefNo(referenceNo);
            transaction.setAccountId(request.getSourceAccountId());
            transaction.setDateOfTransaction(LocalDate.now());
            transaction.setDeposit(0.0);
            transaction.setWithdraw(request.getAmount());
            transaction.setClosingBalance(sourceAccount.get().getBalance());
            transaction.setNarration("to" + request.getTargetAccountId());

            transactionRepo.save(destTransaction);
            return status;
        }
        return status;

    }

    @Override
    public List<Transaction> getTransactions(Long customerId) {
        List<Transaction> transactions = new ArrayList<Transaction>();

        List<Account> accounts = transactionProxy.getCustomerAccounts(customerId);

        for(Account account: accounts){
            transactions.addAll(transactionRepo.findByAccountId(account.getAccountId()));
        }
        return transactions;

    }

    @Override
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.addAll(transactionRepo.findByAccountId(accountId));
        return transactions;
    }


}
