package com.clairvoyant.services.skillmatrix.model;

import com.clairvoyant.services.skillmatrix.model.superclass.Persistable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Lob
    @Column(name = "password", length = 45)
    private String password;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "reporting_to")
    private User reportingManager;

    @Column(name="is_active", length = 45)
    private boolean isActive;

}
