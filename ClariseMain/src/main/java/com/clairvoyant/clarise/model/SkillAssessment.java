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
@Table(name = "skill_assessment")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class SkillAssessment extends Persistable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assessment_id" , referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    private Assessment assessment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skill_category_id" , referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    private SkillCategory skillCategory;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skill_rating_id")
    @Fetch(FetchMode.JOIN)
    private SkillsRating skillsRating;
}





