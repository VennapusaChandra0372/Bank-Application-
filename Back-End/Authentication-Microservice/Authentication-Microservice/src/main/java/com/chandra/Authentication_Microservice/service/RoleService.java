package com.chandra.Authentication_Microservice.service;

import com.chandra.Authentication_Microservice.entity.Role;
import com.chandra.Authentication_Microservice.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role){
       return roleDao.save(role);
    }
}
