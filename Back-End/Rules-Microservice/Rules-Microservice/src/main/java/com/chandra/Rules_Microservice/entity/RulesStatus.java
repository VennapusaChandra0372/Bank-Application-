package com.chandra.Rules_Microservice.entity;

import org.springframework.stereotype.Component;

@Component
public class RulesStatus {

    private static final Float serviceCharges=12.00f;
    private static final Double minBalance = 1000.00;

    private String status;

    public RulesStatus(){

    }

    public RulesStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Float getServiceCharges(){
        return serviceCharges;
    }

    public static Double getMinBalance(){
        return minBalance;
    }

    @Override
    public String toString() {
        return "RulesStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
