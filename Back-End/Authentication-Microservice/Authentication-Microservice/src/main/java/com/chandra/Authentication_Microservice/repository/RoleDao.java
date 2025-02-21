package com.chandra.Authentication_Microservice.repository;

import com.chandra.Authentication_Microservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role,String> {
}
