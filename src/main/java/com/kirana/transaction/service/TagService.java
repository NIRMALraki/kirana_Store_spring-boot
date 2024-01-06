package com.kirana.transaction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kirana.transaction.custom.exception.EmptyInputException;
import com.kirana.transaction.entity.Transaction;
import com.kirana.transaction.repository.TagRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TagService {

	@Autowired
	TagRepository tagRepository;

	public List<Transaction> getAllTransactionByTag(String tag) {
		if (tag == null || tag.isEmpty()) {
			log.error("Tag is emplty");
			throw new EmptyInputException("603", "Invalid Tag");

		}
		log.info("fetching Transactions by tag");
		List<Transaction> transactions = new ArrayList<>();
		try {
			transactions = tagRepository.findByName(tag).get(0).getTransactions();
		} catch (Exception e) {
			log.error(" invalid tag", e);
		}
		return transactions;
	}

}
