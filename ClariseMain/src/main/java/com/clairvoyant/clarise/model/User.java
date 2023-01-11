package com.clairvoyant.clarise.model;

import com.clairvoyant.clarise.model.superclass.Persistable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="user")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User extends Persistable {


    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "email", unique = true, length = 45)
    private String emailAddress;

    @Column(name = "grade", length = 45)
    private String grade;

    @Column(name = "password", length = 45)
    private String password;

    @Column(name = "reporting_to", length = 45)
    private String reportingManager;

    //relationship with Designation
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "designation_id",unique = true)
    private Designation designation;

    //relationship with UserRoleMapping
    @OneToMany( cascade = {CascadeType.REFRESH , CascadeType.PERSIST ,
            CascadeType.DETACH , CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<UserRoleMapping> userRoleMappings;

    //relationship with UserCategoryMapping
    @OneToMany( cascade = {CascadeType.REFRESH , CascadeType.PERSIST ,
            CascadeType.DETACH , CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<UserCategoryMapping> userCategoryMappings;

}
