package com.clairvoyant.clarise.model;

import com.clairvoyant.clarise.model.superclass.Persistable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "designations")
public class Designation extends Persistable {

	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "is_active")
	private boolean isActive;

//	@OneToOne(mappedBy = "designation" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
//	private User user;

	//UserDesignationMapping

}
