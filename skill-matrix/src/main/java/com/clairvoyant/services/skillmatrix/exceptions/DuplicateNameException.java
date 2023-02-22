package com.clairvoyant.services.skillmatrix.exceptions;

public class DuplicateNameException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateNameException(String message) {
        super(message);
    }

    public DuplicateNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
