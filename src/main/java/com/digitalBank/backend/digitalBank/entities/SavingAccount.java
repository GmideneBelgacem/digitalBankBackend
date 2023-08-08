package com.digitalBank.backend.digitalBank.entities;

import java.util.Date;
import java.util.List;

import com.digitalBank.backend.digitalBank.enums.AccountStatus;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("SAV")
public class SavingAccount extends BankAccount{
 private double interestRate;

public double getInterestRate() {
	return interestRate;
}

public void setInterestRate(double interestRate) {
	this.interestRate = interestRate;
}

public SavingAccount(String id, double balance, Date creationDate, AccountStatus status, Customer customer,
		List<AccountOperation> operationList, double interestRate) {
	super(id, balance, creationDate, status, customer, operationList);
	this.interestRate = interestRate;
}

public SavingAccount() {
	super();
	//TODO Auto-generated constructor stub
}

public SavingAccount(String id, double balance, Date creationDate, AccountStatus status, Customer customer,
		List<AccountOperation> operationList) {
	super(id, balance, creationDate, status, customer, operationList);
	//TODO Auto-generated constructor stub
}
 
}
