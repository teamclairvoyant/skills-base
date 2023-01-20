package com.clairvoyant.clarise.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.clairvoyant.clarise.model.superclass.Persistable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "user")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User extends Persistable {

	@Column(name = "user_name", unique = true, length = 45)
	private String userName;

	@Column(name = "password")
	private String password;

}
