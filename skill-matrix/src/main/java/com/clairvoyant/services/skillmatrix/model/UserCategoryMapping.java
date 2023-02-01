package com.clairvoyant.services.skillmatrix.model;

import com.clairvoyant.services.skillmatrix.model.superclass.Persistable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
