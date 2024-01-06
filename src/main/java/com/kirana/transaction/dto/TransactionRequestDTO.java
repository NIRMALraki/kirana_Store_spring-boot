package com.kirana.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionRequestDTO {
	
	private String emailId;
	private double amount;
	private String currency;
	private String transactionDate;
	private String description;
	private String transactionType;
	private int tagId;

}
