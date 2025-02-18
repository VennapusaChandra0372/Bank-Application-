package com.chandra.Rules_Microservice.service;

import com.chandra.Rules_Microservice.dto.Account;
import com.chandra.Rules_Microservice.entity.RulesStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RulesService {

    @Autowired
    private RulesProxy proxy;

    @Autowired
    private RulesStatus status;


    public RulesStatus evaluateMinBalance(Double balance, Long accountId) {
        Optional<Account> account = proxy.getAccount(accountId);
        if(account.isPresent()){
            Double balanceAfterServiceCharges=proxy.serviceChargesDeduction(account.get());

            if(balanceAfterServiceCharges-balance>=RulesStatus.getMinBalance() && balance>0){
                status.setStatus("allowed");
                return status;

            }
            else{
                status.setStatus("denied");
                return status;
            }
        }
        else{
            status.setStatus("NA");
            return status;
        }
    }

    public Float getServiceCharges(){
        return RulesStatus.getServiceCharges();
    }
}
