package com.chandra.Account_Microservice.service;

import com.chandra.Account_Microservice.dto.RulesStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="RULES-MICROSERVICE")
public interface AccountProxy {

    @GetMapping("/evaluateMinBal/{balance}/{accountId}")
    public RulesStatus evaluateMinBalance(@PathVariable("balance") Double balance, @PathVariable("accountId") Long accountId);
}
