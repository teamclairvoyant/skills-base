package com.prd.skillbase.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "employee")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User implements Serializable {

//    Fields of the Database

//    This way you can create generatable id of String type:
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "email", unique = true, length = 45)
    private String email;


    //relationship with Employee_skill
    @OneToMany( cascade = {CascadeType.REFRESH , CascadeType.PERSIST ,
            CascadeType.DETACH , CascadeType.MERGE})
    @JoinColumn(name = "emp_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<EmployeeSkill> employeeSkills;

//      Constructor

    public User() {
    }

//    Specifically request username

    public User(String email) {
        this.email = email;
    }

    //      Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public List<EmployeeSkill> getEmployeeSkills() {
        return employeeSkills;
    }

    public void setEmployeeSkills(List<EmployeeSkill> employeeSkills) {
        this.employeeSkills = employeeSkills;
    }


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


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}