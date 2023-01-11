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
@Table(name = "assesement")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Assesement extends Persistable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    private User user;

    /*@OneToOne( cascade = {CascadeType.REFRESH , CascadeType.PERSIST ,
            CascadeType.DETACH , CascadeType.MERGE})*/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assesementstatus_id", referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    private AssesementStatus assesementStatus;

   /* @OneToOne( cascade = {CascadeType.REFRESH , CascadeType.PERSIST ,
            CascadeType.DETACH , CascadeType.MERGE})*/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assesementtype_id", referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    private AssesementType assesementType;

    @Column(name = "comments", length = 100)
    private String comments;
}





