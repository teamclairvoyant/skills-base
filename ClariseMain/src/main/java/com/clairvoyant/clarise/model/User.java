package com.clairvoyant.clarise.model;

import com.clairvoyant.clarise.model.superclass.Persistable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Data
@Table(name="user")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User extends Persistable {


    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "email", unique = true, length = 45)
    private String emailAddress;

    @Column(name = "grade", length = 45)
    private String grade;

    @Column(name = "password", length = 45)
    private String password;

    @Column(name = "reporting_to", length = 45)
    private String reportingManager;

    @ManyToMany
    Set<UserCategoryMapping> userCategory;

    @ManyToMany
    Set<UserRoleMapping> userRole;


    //Empl No	Empl Name	Grade	Title	Project	Role	RM	Total Exp.	CV exp.
}
