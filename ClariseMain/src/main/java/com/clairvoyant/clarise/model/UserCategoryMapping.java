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
@Table(name="user_category_mapping")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class UserCategoryMapping extends Persistable {

//    relationship with User model
    @ManyToOne(fetch = FetchType.LAZY, optional = false , cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    private User user;

    //relationship with Category model
    @ManyToOne(fetch = FetchType.LAZY, optional = false , cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id" , referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    private Category category;

}
