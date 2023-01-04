package com.clairvoyant.clarise.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST,
            CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "emp_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<EmployeeSkill> employeeSkills;

    public void addEmployeeSkills(EmployeeSkill empSkill) {
        if (employeeSkills == null) {
            employeeSkills = new ArrayList<>();
        }

        employeeSkills.add(empSkill);
    }


    public void deleteSkill(EmployeeSkill employeeSkill) {

        if (employeeSkills == null) {
            employeeSkills = new ArrayList<>();
        }

        employeeSkills.remove(employeeSkill);
    }

}
