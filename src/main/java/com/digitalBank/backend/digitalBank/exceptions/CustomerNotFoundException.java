package com.digitalBank.backend.digitalBank.exceptions;

public class CustomerNotFoundException extends Exception {
private String message;

public CustomerNotFoundException(String message) {
	super(message);
}

}
