package com.chandra.Account_Microservice.serviceImpl;

import com.chandra.Account_Microservice.dto.RulesStatus;
import com.chandra.Account_Microservice.dto.Transaction;
import com.chandra.Account_Microservice.entity.*;
import com.chandra.Account_Microservice.repository.AccountRepository;
import com.chandra.Account_Microservice.service.AccountProxy;
import com.chandra.Account_Microservice.service.AccountProxyForTransactions;
import com.chandra.Account_Microservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private AccountProxy proxy;

    @Autowired
    private AccountProxyForTransactions transactionProxy;

    @Override
    public AccountCreationStatus createAccount(Long customerId) {
        Account savingsAccount= new Account();
        savingsAccount.setAccountId(new Random().nextLong(100000000000L, 999999999999L));
        savingsAccount.setAccountType("Savings");
        savingsAccount.setBalance(1000.00);
        savingsAccount.setCustomerId(customerId);
        savingsAccount.setLastCheckDate(LocalDate.now());
        accountRepo.save(savingsAccount);

        Account currentAccount = new Account();
        currentAccount.setAccountId(new Random().nextLong(100000000000L, 999999999999L));
        currentAccount.setAccountType("Current");
        currentAccount.setBalance(1000.00);
        currentAccount.setCustomerId(customerId);
        currentAccount.setLastCheckDate(LocalDate.now());
        accountRepo.save(currentAccount);

        AccountCreationStatus status = new AccountCreationStatus();
        status.setAccountId(currentAccount.getAccountId());
        status.setMessage("Account Created Succesfully"+ savingsAccount.getAccountId());
        return status;

    }

    @Override
    public List<Account> getCustomerAccounts(Long customerId) {
        return accountRepo.findByCustomerId(customerId);
    }

    @Override
    public Optional<Account> getAccount(Long accountId) {
        return accountRepo.findById(accountId);
    }

    @Override
    public List<Statement> getAccountStatement(Long accountId, LocalDate fromDate, LocalDate toDate) {

        List<Statement> AccountStatements = new ArrayList<Statement>();
        List<Transaction> transactions = transactionProxy.getTransactionsByAccountId(accountId);

        for(Transaction transaction: transactions){
            if(transaction.getDateOfTransaction().compareTo(fromDate)>=0 && transaction.getDateOfTransaction().compareTo(toDate)<0){
                Statement statement = new Statement();

                statement.setDate(transaction.getDateOfTransaction());
                statement.setDeposit(transaction.getDeposit());
                statement.setWithdraw(transaction.getWithdraw());
                statement.setRefNo(transaction.getRefNo());
                statement.setDescription(transaction.getNarration());
                statement.setClosingBalance(transaction.getClosingBalance());

                AccountStatements.add(statement);
            }
        }
        return AccountStatements;
    }

    @Override
    public TransactionStatus deposit(TransactionRequest depositRequest) {
        Optional<Account> account = accountRepo.findById(depositRequest.getAccountId());

        TransactionStatus status = new TransactionStatus();
        if(account.isPresent()){
            account.get().setBalance(account.get().getBalance()+ depositRequest.getAmount());
            accountRepo.save(account.get());
            status.setMessage("Amount of "+ depositRequest.getAmount() + "Sucessfully deposited in to your account");
            status.setSourceBalance(account.get().getBalance());
            status.setDestinationBalance(0.0);
            return status;
        }
        else{
            status.setMessage("Account Not Found...");
            return status;
        }
    }

    @Override
    public TransactionStatus withdraw(TransactionRequest withdrawRequest) {

        RulesStatus status = proxy.evaluateMinBalance(withdrawRequest.getAmount(), withdrawRequest.getAccountId());
        Optional<Account> account = accountRepo.findById(withdrawRequest.getAccountId());

        TransactionStatus transactionStatus = new TransactionStatus();
        if(account.isPresent()){
           if(status.getStatus().equals("allowed")){
               account.get().setBalance(account.get().getBalance()-withdrawRequest.getAmount());
               accountRepo.save(account.get());

               transactionStatus.setMessage("Amount of" + withdrawRequest.getAmount()+"debited from your account");
               transactionStatus.setSourceBalance(account.get().getBalance());
               transactionStatus.setDestinationBalance(0.0);
               return transactionStatus;
           }
           else if(status.getStatus().equals("denied")){
               transactionStatus.setMessage("Insufficient funds ...");
               transactionStatus.setSourceBalance(account.get().getBalance());
               transactionStatus.setDestinationBalance(0.0);
               return transactionStatus;
           }else{
               transactionStatus.setMessage("Account not found ...");
               transactionStatus.setSourceBalance(0.0);
               transactionStatus.setDestinationBalance(0.0);
               return transactionStatus;
           }
        }
        else{
            transactionStatus.setMessage("Account not found ...");
            transactionStatus.setSourceBalance(0.0);
            transactionStatus.setDestinationBalance(0.0);
            return transactionStatus;
        }

    }

    @Override
    public Double deductServiceCharges(Account account){
        LocalDate currentDate = LocalDate.now();
        LocalDate lastCheckedDate = account.getLastCheckDate();

        long difference = currentDate.toEpochDay()-lastCheckedDate.toEpochDay();
        if(difference>=30){
            Float serviceCharges = RulesStatus.getServiceCharges();
            Double currentBalance = account.getBalance();
            Double newBalance = currentBalance-serviceCharges*((int)difference/30);

            account.setBalance(newBalance);
            account.setLastCheckDate(currentDate);

            accountRepo.save(account);
        }
        return account.getBalance();
    }
}
