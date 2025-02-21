package com.chandra.Authentication_Microservice.repository;

import com.chandra.Authentication_Microservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, String> {
}
