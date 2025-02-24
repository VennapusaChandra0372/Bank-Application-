package com.chandra.Account_Microservice.repository;

import com.chandra.Account_Microservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public List<Account> findByCustomerId(Long customerId);
}
