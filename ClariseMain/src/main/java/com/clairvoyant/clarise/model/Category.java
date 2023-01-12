package com.clairvoyant.clarise.model;

import com.clairvoyant.clarise.model.superclass.Persistable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name="category")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Category extends Persistable {

    @Column(name = "cat_name")
    private String catName;

    private String description;

    @Column(name = "is_active")
    private boolean isActive;

}
