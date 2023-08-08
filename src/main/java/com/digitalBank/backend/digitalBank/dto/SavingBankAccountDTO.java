package com.digitalBank.backend.digitalBank.dto;

import java.util.Date;

import com.digitalBank.backend.digitalBank.enums.AccountStatus;
public class SavingBankAccountDTO extends BankAccountDTO{
	private String id;
	private double balance;
	private Date creationDate;
	private AccountStatus status;
	private CustomerDTO customerDTO;
	private double interestRate;
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public AccountStatus getStatus() {
		return status;
	}
	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	public CustomerDTO getCustomer() {
		return customerDTO;
	}
	public void setCustomer(CustomerDTO customer) {
		this.customerDTO = customer;
	}

	public SavingBankAccountDTO(String id, double balance, Date creationDate, AccountStatus status, CustomerDTO customerDTO,
			Long interestRate) {
		super();
		this.id = id;
		this.balance = balance;
		this.creationDate = creationDate;
		this.status = status;
		this.customerDTO = customerDTO;
		this.interestRate = interestRate;
	}
	public SavingBankAccountDTO() {
		super();
		//TODO Auto-generated constructor stub
	}
	
}
