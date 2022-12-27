package com.clairvoyant.clarise.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.clairvoyant.clarise.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<String> noResourceFound(ResourceNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
