package com.digitalBank.backend.digitalBank.entities;

import java.util.Date;
import java.util.List;

import com.digitalBank.backend.digitalBank.enums.AccountStatus;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 5, discriminatorType = DiscriminatorType.STRING)
public class BankAccount {
	@Id
	private String id;
	private double balance;
	private Date creationDate;
	@Enumerated(EnumType.STRING)
	private AccountStatus status;
	@ManyToOne
	private Customer customer;
	@OneToMany(mappedBy = "bankAccount")
	private List<AccountOperation> operationList;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<AccountOperation> getOperationList() {
		return operationList;
	}
	public void setOperationList(List<AccountOperation> operationList) {
		this.operationList = operationList;
	}
	public BankAccount(String id, double balance, Date creationDate, AccountStatus status, Customer customer,
			List<AccountOperation> operationList) {
		super();
		this.id = id;
		this.balance = balance;
		this.creationDate = creationDate;
		this.status = status;
		this.customer = customer;
		this.operationList = operationList;
	}
	public BankAccount() {
		super();
		//TODO Auto-generated constructor stub
	}
	
}
