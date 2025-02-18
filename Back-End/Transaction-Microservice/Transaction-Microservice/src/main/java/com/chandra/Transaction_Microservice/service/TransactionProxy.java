package com.chandra.Transaction_Microservice.service;

import com.chandra.Transaction_Microservice.dto.Account;
import com.chandra.Transaction_Microservice.entity.TransactionRequest;
import com.chandra.Transaction_Microservice.entity.TransactionStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(value="ACCOUNT-MICROSERVICE")
public interface TransactionProxy {

    @PostMapping("/accounts/deposit")
    public TransactionStatus deposit(@RequestBody TransactionRequest depositRequest);

    @PostMapping("accounts//withdraw")
    public TransactionStatus withdraw(@RequestBody TransactionRequest withdrawRequest);

    @GetMapping("/accounts/getCustomerAccounts/{customerId}")
    public List<Account> getCustomerAccounts(@PathVariable("customerId") Long id);

    @GetMapping("/accounts/getAccount/{accountId}")
    public Optional<Account> getAccount(@PathVariable("accountId") Long id);
}
