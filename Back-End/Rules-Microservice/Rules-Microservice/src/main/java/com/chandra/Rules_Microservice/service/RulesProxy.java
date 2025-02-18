package com.chandra.Rules_Microservice.service;

import com.chandra.Rules_Microservice.dto.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(value="ACCOUNT-MICROSERVICE")
public interface RulesProxy {

    @GetMapping("/getAccount/{accountId}")
    public Optional<Account> getAccount(@PathVariable("accountId") Long id);


    @PostMapping("/accounts/deductServiceCharges")
    public Double serviceChargesDeduction(@RequestBody Account account);
}
