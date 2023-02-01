package com.clairvoyant.services.skillmatrix.model;

import com.clairvoyant.services.skillmatrix.model.superclass.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Table(name = "designations")
@NoArgsConstructor
@AllArgsConstructor
public class Designation extends Persistable {

	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "is_active")
	private boolean isActive;
}
