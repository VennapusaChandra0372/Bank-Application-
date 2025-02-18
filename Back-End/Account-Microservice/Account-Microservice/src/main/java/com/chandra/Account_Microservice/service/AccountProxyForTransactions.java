package com.chandra.Account_Microservice.service;

import com.chandra.Account_Microservice.dto.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value="TRANSACTION-MICROSERVICE")
public interface AccountProxyForTransactions {
    @GetMapping("/getTransactionsByAccountId/{accountId}")
    public List<Transaction> getTransactionsByAccountId(@PathVariable("accountId") Long accountId);
}
