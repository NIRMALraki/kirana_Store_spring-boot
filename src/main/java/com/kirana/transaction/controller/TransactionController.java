package com.kirana.transaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kirana.transaction.dto.TransactionRequestDTO;
import com.kirana.transaction.entity.Transaction;
import com.kirana.transaction.service.TagService;
import com.kirana.transaction.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/")
@Slf4j
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@Autowired
	TagService tagService;

	@Operation(summary = "Records a new transaction", description = "Creates a new transaction in transaction database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Transaction successfully logged") })
	@PostMapping("/transaction")
	public ResponseEntity<String> recordTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {

		log.info("Transaction as being logged in the database");
		return ResponseEntity.ok(transactionService.recordTransaction(transactionRequestDTO));
	}

	@Operation(summary = "Retrives transactions by Transaction Type", description = "Retrives transactions for type Credit and Debit as a input")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Transaction is retrived sucessfully") })
	@GetMapping("/transaction/{transactionType}")
	public ResponseEntity<List<Transaction>> transactionByType(
			@PathVariable("transactionType") String transactionType) {
		log.info("Fetching transactions");
		return ResponseEntity.ok(transactionService.getTransactionByTrasactionType(transactionType));
	}

	@Operation(summary = "Retrives transactions by Transaction date", description = "Retrives transactions with respect to date and the pattern of the date should be yyyyMMdd")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Transaction is retrived sucessfully") })
	@GetMapping("/transaction/date/{transactionDate}")
	public ResponseEntity<List<Transaction>> transactionByDate(
			@PathVariable("transactionDate") String transactionDate) {
		log.info("Fetching transactions by date");
		return ResponseEntity.ok(transactionService.getTransactionByDate(transactionDate));
	}

	@Operation(summary = "Retrives transactions by Transaction tag", description = "Retrives transactions by tag name ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Transaction is retrived sucessfully") })
	@GetMapping("/transaction/tag/{tagname}")
	public ResponseEntity<List<Transaction>> transactionByTagName(@PathVariable("tagname") String tagName) {
		log.info("Fetching transactions by tagname");
		return ResponseEntity.ok(tagService.getAllTransactionByTag(tagName));
	}

}
