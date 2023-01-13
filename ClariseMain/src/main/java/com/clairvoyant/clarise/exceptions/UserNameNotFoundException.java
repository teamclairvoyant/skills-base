package com.clairvoyant.clarise.exceptions;

public class UserNameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserNameNotFoundException(String message) {
		super(message);
	}
	
	public UserNameNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
}
