package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.model.Skill;
import com.clairvoyant.clarise.model.Employee;
import com.clairvoyant.clarise.repository.EmployeeSkillRepository;
import com.clairvoyant.clarise.repository.SkillRepository;
import com.clairvoyant.clarise.repository.EmployeeRepository;
import com.clairvoyant.clarise.model.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeSkillRepository employeeSkillRepository;


    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }


    @Override
    public void updateSkill(String empId, String skillId , EmployeeSkill employeeSkill) {


        Optional<Employee> emp = employeeRepository.findById(empId);

        Optional<Skill> tempSkill = skillRepository.findById(skillId);

        EmployeeSkill empSkill = new EmployeeSkill();

        empSkill.setEmployee(emp.get());
        empSkill.setSkill(tempSkill.get());
        empSkill.setRating(employeeSkill.getRating());

        emp.get().addEmployeeSkills(empSkill);

        employeeRepository.save(emp.get());

    }

    @Override
    public void deleteSkills(String employeeSkillId) {

        employeeSkillRepository.deleteById(employeeSkillId);

    }

}