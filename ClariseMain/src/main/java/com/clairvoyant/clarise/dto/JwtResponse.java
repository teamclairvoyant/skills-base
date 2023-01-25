package com.clairvoyant.clarise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	

	private String name;
	private String emailAddress;
	private String reportingManager;
	private String grade;
	private String token;

}

