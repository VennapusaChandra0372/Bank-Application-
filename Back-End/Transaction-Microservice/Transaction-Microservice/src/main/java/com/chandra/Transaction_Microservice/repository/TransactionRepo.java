package com.chandra.Transaction_Microservice.repository;

import com.chandra.Transaction_Microservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    public List<Transaction> findByAccountId(Long accountId);
}
