package com.clairvoyant.clarise.model;

import com.clairvoyant.clarise.model.superclass.Persistable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name="assessment")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Assessment extends Persistable {

    private String comments;

    @OneToMany
    @JoinColumn(name = "user_id")
    Set<User> users;

    @OneToMany
    @JoinColumn(name = "category_id")
    Set<Category> category;

    @OneToMany
    @JoinColumn(name = "assessment_status_id")
    Set<AssessmentStatus> assessmentStatus;

    @OneToMany
    @JoinColumn(name = "assessment_type_id")
    Set<AssessmentType> assessmentType;


}
