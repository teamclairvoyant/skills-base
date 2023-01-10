package com.clairvoyant.clarise.model;

import com.clairvoyant.clarise.model.superclass.Persistable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Data
@Table(name="assessment")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Assessment extends Persistable {

    private String comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Set<User> users;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Set<Category> category;

    @OneToOne
    @JoinColumn(name = "assessment_status_id")
    Set<AssessmentStatus> assessmentStatus;

    @OneToOne
    @JoinColumn(name = "assessment_type_id")
    Set<AssessmentType> assessmentType;


}
