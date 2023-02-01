package com.clairvoyant.clarise.dto;

import com.clairvoyant.clarise.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

	private String name;
	private String emailAddress;
	private User reportingManager;
	private String grade;
	private String token;
}
