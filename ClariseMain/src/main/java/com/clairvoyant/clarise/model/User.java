package com.clairvoyant.clarise.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.clairvoyant.clarise.model.superclass.Persistable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Data
@Table(name = "user")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User extends Persistable {

	@Column(name = "user_name", unique = true, length = 45)
	private String userName;

	@Column(name = "password")
	private String password;

	//setter and getters
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	
}
