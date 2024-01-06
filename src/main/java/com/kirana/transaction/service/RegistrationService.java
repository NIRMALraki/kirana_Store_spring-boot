package com.kirana.transaction.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.kirana.transaction.custom.exception.EmptyInputException;
import com.kirana.transaction.entity.User;
import com.kirana.transaction.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegistrationService {

	@Autowired
	UserRepository userRepository;

	@PostMapping("\register")
	public String createUser(User user) {
		if (user == null || user.getEmail().isEmpty()) {
			log.error("User details not entered");
			throw new EmptyInputException("601", "User details not entered");
		}
		if (userRepository.existsByEmail(user.getEmail())) {
			log.warn("user already exist");
			return "user already exist";
		}
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setRegistrationDate(new Date());
		userRepository.save(user);
		log.info("User Created");
		return "User Created";
	}

}
