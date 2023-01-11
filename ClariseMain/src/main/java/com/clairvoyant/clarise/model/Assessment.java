package com.clairvoyant.clarise.model;

import com.clairvoyant.clarise.model.superclass.Persistable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

//skills model
@Entity
@Data
@Table(name = "assessment")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Assessment extends Persistable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assessment_status_id", referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    private AssessmentStatus assessmentStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assessment_type_id", referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    private AssessmentType assessmentType;

    @Column(name = "comments", length = 100)
    private String comments;
}





