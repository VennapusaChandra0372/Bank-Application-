package com.chandra.Customer_Microservice.entity;

import jakarta.persistence.*;

public class CustomerCreationStatus {

    private Long customer_id;
    private String message;



    public CustomerCreationStatus(){

    }

    public CustomerCreationStatus(Long customer_id, String message) {
        this.customer_id = customer_id;
        this.message = message;

    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    @Override
    public String toString() {
        return "CustomerCreationStatus{" +
                "customer_id=" + customer_id +
                ", message='" + message + '\'' +
                '}';
    }
}
