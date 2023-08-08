package com.digitalBank.backend.digitalBank.exceptions;

public class BalanceNotSufficientException extends Exception {
private String message;

public BalanceNotSufficientException(String message) {
	super(message);
	this.message = message;
}

}
