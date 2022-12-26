package com.clairvoyant.clarise.exceptions;

public class SkillRatingNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SkillRatingNotFoundException(String message) {
    	super(message);
    }
}
