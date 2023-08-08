package com.digitalBank.backend.digitalBank.dto;

import java.util.Date;

import com.digitalBank.backend.digitalBank.enums.OperationType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class AccountOperationDTO {
	private Long id;
	private Date operationDate;
	private double amount;
	@Enumerated(EnumType.STRING)
	private OperationType operationType;
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


	
}
