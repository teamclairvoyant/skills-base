package com.clairvoyant.clarise.model;

import com.clairvoyant.clarise.model.superclass.Persistable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

//skills model
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "skills")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Skill extends Persistable {

    @Column(name = "skill_name")
    private String skillName;
    private String description;
    @Column(name = "is_active")
    private boolean isActive;
}





