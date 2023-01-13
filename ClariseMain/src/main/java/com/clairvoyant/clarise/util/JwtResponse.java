package com.clairvoyant.clarise.util;

import lombok.Data;

@Data
public class JwtResponse {
	
	private String userName;
	private String JwtToken;
		
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getJwtToken() {
		return JwtToken;
	}

	public void setJwtToken(String jwtToken) {
		JwtToken = jwtToken;
	} 

	public JwtResponse(String userName, String JwtToken) {
		this.userName = userName;
		this.JwtToken = JwtToken;
	}

}
