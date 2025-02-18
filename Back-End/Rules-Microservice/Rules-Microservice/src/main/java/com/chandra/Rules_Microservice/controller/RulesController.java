package com.chandra.Rules_Microservice.controller;

import com.chandra.Rules_Microservice.entity.RulesStatus;
import com.chandra.Rules_Microservice.service.RulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rules")
public class RulesController {

    @Autowired
    private RulesService rulesService;

    @GetMapping("/evaluateMinBal/{balance}/{accountId}")
    public RulesStatus evaluateMinBalance(@PathVariable("balance") Double balance, @PathVariable("accountId") Long accountId){
        return rulesService.evaluateMinBalance(balance,accountId);
    }

    @GetMapping("/getServiceCharges")
    public Float getServiceCharges(){
        return rulesService.getServiceCharges();
    }
}
