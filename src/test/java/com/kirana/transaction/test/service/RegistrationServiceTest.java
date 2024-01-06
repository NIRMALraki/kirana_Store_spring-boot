package com.kirana.transaction.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kirana.transaction.entity.User;
import com.kirana.transaction.repository.UserRepository;
import com.kirana.transaction.service.RegistrationService;

public class RegistrationServiceTest {
	
	@InjectMocks
	RegistrationService registrationService;
	
	@Mock
	UserRepository userRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testCreateUser_UserCreated(){
		
		User user = new User();
		user.setEmail("user1@gmail.com");
		user.setName("user1");
		user.setPassword("user1");
		user.setRegistrationDate(new Date());
		user.setRole("user");
		
		when(userRepository.save(user)).thenReturn(user);
		
		String result = registrationService.createUser(user);
		assertEquals("User Created", result);
		
		
	}
	
	@Test
	void testCreateUser_UserAlreadyExist(){
		
		User user = new User();
		user.setEmail("user1@gmail.com");
		user.setName("user1");
		user.setPassword("user1");
		user.setRegistrationDate(new Date());
		user.setRole("user");
		
		when(userRepository.existsByEmail("user1@gmail.com")).thenReturn(true);
		
		String result = registrationService.createUser(user);
		assertEquals("user already exist", result);
		
		
	}
	

}
