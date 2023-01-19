package com.clairvoyant.clarise.model;

import com.clairvoyant.clarise.model.superclass.Persistable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name="user_designation_mapping")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class UserDesignationMapping extends Persistable {

    //relationship with User model
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user;

    //relationship with Designation model
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "designation_id" , referencedColumnName = "id")
    private Designation designation;

    private boolean isActive;

}
