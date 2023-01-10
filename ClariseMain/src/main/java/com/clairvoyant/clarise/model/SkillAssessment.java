package com.clairvoyant.clarise.model;

import com.clairvoyant.clarise.model.superclass.Persistable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Data
@Table(name="skill_assessment")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class SkillAssessment extends Persistable {

    @ManyToOne(targetEntity=Assessment.class,fetch = FetchType.LAZY )
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

    @ManyToOne(targetEntity=Category.class,fetch = FetchType.LAZY )
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(targetEntity=Skill.class,fetch = FetchType.LAZY )
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @ManyToOne(targetEntity=SkillsRating.class,fetch = FetchType.LAZY, optional = false )
    @JoinColumn(name = "skills_rating_id")
    private SkillsRating skillsRating;

}
