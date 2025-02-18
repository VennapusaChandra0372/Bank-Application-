package com.chandra.Customer_Microservice.service;



import com.chandra.Customer_Microservice.dto.AccountCreationStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name= "ACCOUNT-MICROSERVICE")
public interface CustomerProxy {

    @PostMapping("/createAccount/{customerId}")
    public AccountCreationStatus createAccount(@PathVariable("customerId") Long id);

}
