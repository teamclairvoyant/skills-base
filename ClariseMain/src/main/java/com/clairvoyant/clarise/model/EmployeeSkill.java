package com.clairvoyant.clarise.model;

import com.clairvoyant.clarise.model.superclass.Persistable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

//model for employee skill join table
@Entity
@Data
@Table(name="employee_skill")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class EmployeeSkill extends Persistable {

    //relationship with User model
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "emp_id" , referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    private Employee employee;

    //relationship with Skill model
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skill_id" , referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    private Skill skill;

    @Column(name = "rating")
    private String rating;
}
