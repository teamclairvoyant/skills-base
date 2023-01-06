package com.clairvoyant.clarise.model;

import com.clairvoyant.clarise.model.superclass.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
@Table(name = "designations")
public class Designation extends Persistable {

	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "is_active")
	private boolean isActive;
}
