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
@Table(name = "skill_assesement")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class SkillAssesement extends Persistable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assesement_id" , referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    private Assesement assesement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skillcategory_id" , referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    private SkillCategory skillCategory;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skillsrating_id")
    @Fetch(FetchMode.JOIN)
    private SkillsRating skillsRating;
}





