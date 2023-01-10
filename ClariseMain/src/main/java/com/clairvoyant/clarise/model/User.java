package com.clairvoyant.clarise.model;

import com.clairvoyant.clarise.model.superclass.Persistable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role_mapping",
            joinColumns = @JoinColumn(name = "userId",unique = true),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_category_mapping",
            joinColumns = @JoinColumn(name = "userId",unique = true),
            inverseJoinColumns = @JoinColumn(name = "categoryId"))
    private List<Category> category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "designation_id",unique = true)
    private Designation designation;

    //Empl No	Empl Name	Grade	Title	Project	Role	RM	Total Exp.	CV exp.
}
