package com.digitalBank.backend.digitalBank.entities;

import java.util.Date;
import java.util.List;

import com.digitalBank.backend.digitalBank.enums.AccountStatus;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CAC")
public class CurrentAccount extends BankAccount {
	private double overDraft;

	public double getOverDraft() {
		return overDraft;
	}

	public void setOverDraft(double overDraft) {
		this.overDraft = overDraft;
	}

	public CurrentAccount(String id, double balance, Date creationDate, AccountStatus status, Customer customer,
			List<AccountOperation> operationList, double overDraft) {
		super(id, balance, creationDate, status, customer, operationList);
		this.overDraft = overDraft;
	}

	public CurrentAccount() {
		super();
		//TODO Auto-generated constructor stub
	}

	public CurrentAccount(String id, double balance, Date creationDate, AccountStatus status, Customer customer,
			List<AccountOperation> operationList) {
		super(id, balance, creationDate, status, customer, operationList);
		//TODO Auto-generated constructor stub
	}
	
}
