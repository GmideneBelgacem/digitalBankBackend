package com.digitalBank.backend.digitalBank.dto;

import java.util.Date;

import com.digitalBank.backend.digitalBank.enums.AccountStatus;
public class CurrentBankAccountDTO extends BankAccountDTO{
	private String id;
	private double balance;
	private Date creationDate;
	private AccountStatus status;
	private CustomerDTO customerDTO;
	private double overDraft;

	public double getOverDraft() {
		return overDraft;
	}
	public void setOverDraft(double overDraft) {
		this.overDraft = overDraft;
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
	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}
	public void setCustomerDTO(CustomerDTO customer) {
		this.customerDTO = customer;
	}

	public CurrentBankAccountDTO(String id, double balance, Date creationDate, AccountStatus status, CustomerDTO customerDTO,
			Long overDraft) {
		super();
		this.id = id;
		this.balance = balance;
		this.creationDate = creationDate;
		this.status = status;
		this.customerDTO = customerDTO;
		this.overDraft = overDraft;
	}
	public CurrentBankAccountDTO() {
		super();
		//TODO Auto-generated constructor stub
	}
	
}
