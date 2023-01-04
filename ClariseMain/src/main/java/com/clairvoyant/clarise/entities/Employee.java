package com.clairvoyant.clarise.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "employee")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Employee implements Serializable {

//    Fields of the Database

//    This way you can create generatable id of String type:
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "emp_id")
    private String empId;

    @Column(name = "emp_name", length = 45)
    private String name;

    @Column(name = "email", unique = true, length = 45)
    private String email;

    @Column(name = "role", length = 45)
    private String role;

    //relationship with Employee_skill
    @OneToMany( cascade = {CascadeType.REFRESH , CascadeType.PERSIST ,
            CascadeType.DETACH , CascadeType.MERGE})
    @JoinColumn(name = "emp_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<EmployeeSkill> employeeSkills;

    public void addEmployeeSkills(EmployeeSkill empSkill){
        if(employeeSkills == null){
            employeeSkills = new ArrayList<>();
        }

        employeeSkills.add(empSkill);
    }


    public  void  deleteSkill(EmployeeSkill employeeSkill){

        if (employeeSkills == null){
            employeeSkills= new ArrayList<>();
        }

        employeeSkills.remove(employeeSkill);
    }

}