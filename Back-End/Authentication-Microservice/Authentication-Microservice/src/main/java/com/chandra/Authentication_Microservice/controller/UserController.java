package com.chandra.Authentication_Microservice.controller;

import com.chandra.Authentication_Microservice.entity.Role;
import com.chandra.Authentication_Microservice.entity.User;
import com.chandra.Authentication_Microservice.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void intRolesAndUsers(){
        userService.initRolesAndUser();
    }

    @PostMapping("/registerNewUser")
    public User registerNewUser(@RequestBody User user){
        return userService.registerNewUser(user);
    }

    @GetMapping("/forAdmin")
    public String forAdmin(){
        return "This url is only applicable to admin";
    }

@GetMapping("/forUser")
    public String forUser(){
        return "This url is only accesible to the user";
    }



}
