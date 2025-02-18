package com.chandra.Customer_Microservice.serviceImpl;

import com.chandra.Customer_Microservice.entity.Customer;

import com.chandra.Customer_Microservice.repository.CustomerRepo;
import com.chandra.Customer_Microservice.service.CustomerProxy;
import com.chandra.Customer_Microservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CustomerProxy customerProxy;

    public Customer createCustomer(Customer customer){
        Customer c = customerRepo.save(customer);
        customerProxy.createAccount(customer.getId());
        return c;

    }

    public Customer getCustomerByCustomerId(Long id){
        return customerRepo.findById(id).orElse(null);
    }

    public List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    public Customer updateCustomer(Long id, Customer customer){
        Customer customer1 = customerRepo.findById(id).orElseThrow(()->
        new RuntimeException("Customer Not Found"));

        customer1.setName(customer.getName());
        customer1.setDate_of_birth(customer.getDate_of_birth());
        customer1.setPan(customer.getPan());
        customer1.setEmail(customer.getEmail());
        customer1.setAddress(customer.getAddress());

        return customerRepo.save(customer);
    }
}
