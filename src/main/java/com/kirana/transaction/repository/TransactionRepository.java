package com.kirana.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirana.transaction.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

	List<Transaction> findByTransactionType(String transactionType);
	
	List<Transaction> findByTransactionDateBetween(LocalDateTime date1,LocalDateTime date2);
	
	List<Transaction> findByTransactionDateAfter(LocalDateTime transactionDate);
}
