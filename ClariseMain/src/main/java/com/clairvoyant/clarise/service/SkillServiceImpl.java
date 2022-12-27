package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.model.Skill;
import com.clairvoyant.clarise.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    /*@Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeSkillRepository employeeSkillRepository;*/


    @Override
    public List<Skill> findAll() {
        return skillRepository.findByIsActive(true);
    }

    @Override
    public Skill addOrUpdateSkill(Skill skill) {
        return skillRepository.save(skill);
    }


    /*@Override
    public void updateSkill(String empId, String skillId , EmployeeSkill employeeSkill) {


        Optional<Employee> emp = employeeRepository.findById(empId);

        Optional<Skill> tempSkill = skillRepository.findById(skillId);

        EmployeeSkill empSkill = new EmployeeSkill();

        empSkill.setEmployee(emp.get());
        empSkill.setSkill(tempSkill.get());
        empSkill.setRating(employeeSkill.getRating());

        emp.get().addEmployeeSkills(empSkill);

        employeeRepository.save(emp.get());

    }*/

    @Override
    public Skill findSkill(String skillId) {
        Skill skill = skillRepository.findById(skillId).get();
        return skill;
    }

    @Override
    public void deleteSkill(String skillId) {
        Skill skill = skillRepository.findById(skillId).get();
        if (skill != null) {
            skill.setActive(false);
            skillRepository.save(skill);
        }
    }

}