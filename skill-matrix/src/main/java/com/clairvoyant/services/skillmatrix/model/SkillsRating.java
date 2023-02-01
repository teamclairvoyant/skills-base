package com.clairvoyant.services.skillmatrix.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.clairvoyant.services.skillmatrix.model.superclass.Persistable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "skills_rating")
public class SkillsRating extends Persistable {

	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "is_active")
	private boolean isActive;
}
