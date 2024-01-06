package com.kirana.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kirana.transaction.entity.User;
import com.kirana.transaction.service.RegistrationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/")
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;

	@Operation(summary = "Registers a new user", description = "Creates a new user in user database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Successfully created") })
	@PostMapping("/register")
	public ResponseEntity<String> resgisterUser(@RequestBody User user) {
		log.info(user.getEmail() + " has been created");
		return new ResponseEntity<>(registrationService.createUser(user), HttpStatus.CREATED);

	}

}
