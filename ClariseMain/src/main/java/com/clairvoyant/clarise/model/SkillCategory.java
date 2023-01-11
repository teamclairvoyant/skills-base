package com.clairvoyant.clarise.model;

import com.clairvoyant.clarise.model.superclass.Persistable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Data
@Table(name = "skill_category")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class SkillCategory extends Persistable {

    @ManyToOne
    @JoinColumn(name = "skill_id")
    Skill skill;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @Column(name = "is_active")
    private boolean isActive;

}
