package com.digitalBank.backend.digitalBank.dto;

import java.util.List;

public class AccountHistoryDTO {
private int currentPage; 
private int size; 
private int totalPages;
private List<AccountOperationDTO> operationList; 
private String accountId;
private double balance;
public int getCurrentPage() {
	return currentPage;
}
public void setCurrentPage(int currentPage) {
	this.currentPage = currentPage;
}
public int getSize() {
	return size;
}
public void setSize(int size) {
	this.size = size;
}
public int getTotalPages() {
	return totalPages;
}
public void setTotalPages(int totalPages) {
	this.totalPages = totalPages;
}
public List<AccountOperationDTO> getOperationList() {
	return operationList;
}
public void setOperationList(List<AccountOperationDTO> operationList) {
	this.operationList = operationList;
}
public String getAccountId() {
	return accountId;
}
public void setAccountId(String accountId) {
	this.accountId = accountId;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}

}
