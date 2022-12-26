package com.clairvoyant.clarise.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.clairvoyant.clarise.exceptions.DesignationNotFoundException;
import com.clairvoyant.clarise.exceptions.QualificationStatusNotFoundException;
import com.clairvoyant.clarise.exceptions.SkillRatingNotFoundException;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = SkillRatingNotFoundException.class)
	public ResponseEntity<String> noSkillsRatingFound() {
		return new ResponseEntity<>("Skill Rating Not Found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = QualificationStatusNotFoundException.class)
	public ResponseEntity<String> noQualificationStatusFound() {
		return new ResponseEntity<>("Qualification Status Not Found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = DesignationNotFoundException.class)
	public ResponseEntity<String> noDesignationNotFound() {
		return new ResponseEntity<>("Designation Not Found", HttpStatus.NOT_FOUND);
	}
}
