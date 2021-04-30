package com.clairvoyant.clarise.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//skills model
@Entity
@Data
@Table(name = "skills")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Skill implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="id")
    private String id;

    @Column(name = "skill_name")
    private String skillName;

    //relationship with Employee_Skills
    @OneToMany(cascade = {CascadeType.REFRESH , CascadeType.PERSIST ,
            CascadeType.DETACH , CascadeType.MERGE})
    @JoinColumn(name = "skill_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<EmployeeSkill> employeeSkills;


    public void addEmployeeSkills(EmployeeSkill empSkill){
        if(employeeSkills == null){
            employeeSkills = new ArrayList<>();
        }

        employeeSkills.add(empSkill);
    }
}





