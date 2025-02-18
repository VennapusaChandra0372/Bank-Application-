package com.chandra.Rules_Microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.chandra.Rules_Microservice")
public class RulesMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RulesMicroserviceApplication.class, args);
	}

}
