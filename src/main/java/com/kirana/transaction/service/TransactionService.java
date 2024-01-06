package com.kirana.transaction.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kirana.transaction.common.Util;
import com.kirana.transaction.dto.TransactionRequestDTO;
import com.kirana.transaction.entity.Transaction;
import com.kirana.transaction.entity.User;
import com.kirana.transaction.repository.TagRepository;
import com.kirana.transaction.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	UserService userService;

	@Autowired
	TagRepository tagRepository;

	private final static float INR = 83.0632542663f;

	public String recordTransaction(TransactionRequestDTO transactionRequestDTO) {

		User user = userService.getUserByEmail(transactionRequestDTO.getEmailId());
		if (user == null) {
			log.error(" transactionRequestDTO.getEmailId()",
					new IllegalArgumentException("User not found with the email Id"));
			return " Invalid User";
		}

		Transaction transaction = new Transaction();
		transaction.setUser(user);
		transaction.setTransactionType(transactionRequestDTO.getTransactionType());
		transaction.setCurrency(transactionRequestDTO.getCurrency());
		transaction.setDescription(transactionRequestDTO.getDescription());
		transaction.setAmount(
				calculateAmountWithTargetCurrency(transactionRequestDTO.getAmount(), transaction.getCurrency()));
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.getTags().add(tagRepository.findById(transactionRequestDTO.getTagId()).get());
		transactionRepository.save(transaction);
		return "Transaction as been logged successfully";

	}

	public List<Transaction> getTransactionByTrasactionType(String transactionType) {
		List<Transaction> transactions = new ArrayList<>();
		try {
			transactions = transactionRepository.findByTransactionType(transactionType);
		} catch (Exception e) {
			log.error("error in findind the transaction :" + e.getMessage());
		}

		return transactions;
	}

	public List<Transaction> getTransactionByDate(String date) {
		List<Transaction> transactions = new ArrayList<>();

		try {
			transactions = transactionRepository.findByTransactionDateAfter(Util.parseDateTime(date));
		} catch (Exception e) {

			log.error(date, e);
		}

		return transactions;
	}

	private static double calculateAmountWithTargetCurrency(double amount, String currency) {
		if (currency == "USD") {
			return amount;
		} else {
			return INR * amount;
		}
	}

}
