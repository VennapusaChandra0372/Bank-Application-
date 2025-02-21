package com.chandra.Authentication_Microservice.service;

import com.chandra.Authentication_Microservice.entity.Role;
import com.chandra.Authentication_Microservice.entity.User;
import com.chandra.Authentication_Microservice.repository.RoleDao;
import com.chandra.Authentication_Microservice.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleDao roleDao;

    public User registerNewUser(User user){
        return userDao.save(user);
    }

    public void initRolesAndUser(){
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default Role for newly created User");
        roleDao.save(userRole);


        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword(getEncodedPassword("admin1234"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);


        User user = new User();
        user.setUsername("chandra");
        user.setPassword(getEncodedPassword("chandra1234"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);

    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }
}
