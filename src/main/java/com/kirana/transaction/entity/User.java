package com.kirana.transaction.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Schema(name = "Name", example = "Jaruser")
	private String name;
	@Schema(name = "Email Id", example = "jaruser1@gmail.com")
	private String email;
	@Schema(name = "Password", example = "********")
	private String password;
	private Date registrationDate;
	@Schema(name = "Role", example = "USER")
	private String role;
	@OneToMany
	@JoinColumn(name = "transactionId")
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
	
	
}
