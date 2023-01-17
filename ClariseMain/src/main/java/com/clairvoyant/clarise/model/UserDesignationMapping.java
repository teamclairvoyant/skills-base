package com.clairvoyant.clarise.model;

import com.clairvoyant.clarise.model.superclass.Persistable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Data
@Table(name="user_designation_mapping")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class UserDesignationMapping extends Persistable {

    //relationship with User model
    @OneToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
//    @Fetch(FetchMode.JOIN)
    private User user;

    //relationship with Designation model
    @ManyToOne
    @JoinColumn(name = "designation_id" , referencedColumnName = "id")
//    @Fetch(FetchMode.JOIN)
    private Designation designation;

    private boolean isActive;

}
