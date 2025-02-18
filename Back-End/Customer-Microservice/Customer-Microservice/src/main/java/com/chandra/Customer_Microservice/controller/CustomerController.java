package com.chandra.Customer_Microservice.controller;

import com.chandra.Customer_Microservice.entity.Customer;

import com.chandra.Customer_Microservice.serviceImpl.CustomerServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerSerImpl;

    @PostMapping("/createCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSerImpl.createCustomer(customer));

    }

    @GetMapping("/getCustomerByCustomerId/{id}")
    public ResponseEntity<Customer> getCustomerByCustomerId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerSerImpl.getCustomerByCustomerId(id));
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return  ResponseEntity.status(HttpStatus.OK).body(customerSerImpl.getAllCustomers());
    }


    @PutMapping("/updateCustomerDetails/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        return ResponseEntity.status(HttpStatus.OK).body(customerSerImpl.updateCustomer(id,customer));
    }



}
