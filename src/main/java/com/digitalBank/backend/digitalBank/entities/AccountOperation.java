package com.digitalBank.backend.digitalBank.entities;

import java.util.Date;

import com.digitalBank.backend.digitalBank.enums.OperationType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class AccountOperation {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date operationDate;
	private double amount;
	@Enumerated(EnumType.STRING)
	private OperationType operationType;
	@ManyToOne
	private BankAccount bankAccount;
	private String description;
	public Long getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public OperationType getOperationType() {
		return operationType;
	}
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	public AccountOperation(Long id, Date operationDate, double amount, OperationType operationType,
			BankAccount bankAccount) {
		super();
		this.id = id;
		this.operationDate = operationDate;
		this.amount = amount;
		this.operationType = operationType;
		this.bankAccount = bankAccount;
	}
	public AccountOperation() {
		super();
		//TODO Auto-generated constructor stub
	}
	
}
