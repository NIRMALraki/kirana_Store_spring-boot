package com.kirana.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kirana.transaction.custom.exception.EmptyInputException;
import com.kirana.transaction.entity.User;
import com.kirana.transaction.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User getUserByEmail(String email) {
		if (email == null || email.isEmpty()) {
			log.error("the email entered is empty");
			throw new EmptyInputException("602", "invalid input");
		}
		return userRepository.findByEmail(email);
	}

}
