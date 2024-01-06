package com.kirana.transaction.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreType
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "userId")
	@JsonBackReference
	private User user;
	@Schema(name = "amount", example = "100.00")
	private double amount;
	@Schema(name = "currency", example = "USD")
	private String currency;
	private LocalDateTime transactionDate;
	@Schema(name = "description", example = "Spana book Stores")
	private String description;
	@Schema(name = "transactionType", example = "Credit")
	private String transactionType;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "TRANSACTION_TAG_TABLE", joinColumns = {
			@JoinColumn(name = "Transaction_Id") }, 
			inverseJoinColumns = @JoinColumn(name = "Tag_Id"))
	@JsonManagedReference
	private List<Tag> tags = new ArrayList<>();
	
}
