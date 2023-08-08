package com.digitalBank.backend.digitalBank.exceptions;

public class BankAccountNotFoundException extends Exception{
private String message;

public BankAccountNotFoundException(String message) {
	super(message);
} 

}
