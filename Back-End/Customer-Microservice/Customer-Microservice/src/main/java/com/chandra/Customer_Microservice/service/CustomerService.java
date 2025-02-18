package com.chandra.Customer_Microservice.service;

import com.chandra.Customer_Microservice.entity.Customer;


import java.util.List;

public interface CustomerService {

    public Customer createCustomer(Customer customer);
    public Customer getCustomerByCustomerId(Long id);
    public List<Customer> getAllCustomers();
    public Customer updateCustomer(Long id, Customer customer);
}
