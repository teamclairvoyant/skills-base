package com.clairvoyant.clarise.service;


import com.clairvoyant.clarise.model.Skill;
import com.clairvoyant.clarise.model.User;
import com.clairvoyant.clarise.repository.EmployeeSkillRepository;
import com.clairvoyant.clarise.repository.SkillRepository;
import com.clairvoyant.clarise.repository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private EmployeeSkillRepository employeeSkillRepository;


    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public void save(Skill theSkill) {
        skillRepository.save(theSkill);
    }


    @Override
    public void updateSkill(String empId, String skillId , EmployeeSkill employeeSkill) {


        Optional<User> emp = userRepository.findById(empId);

        Optional<Skill> tempSkill = skillRepository.findById(skillId);

        EmployeeSkill empSkill = new EmployeeSkill();

        empSkill.setUser(emp.get());
        empSkill.setSkill(tempSkill.get());
        empSkill.setRating(employeeSkill.getRating());

        emp.get().addEmployeeSkills(empSkill);

        userRepository.save(emp.get());


    }

    @Override
    public void deleteSkills(String employeeSkillId) {

        employeeSkillRepository.deleteById(employeeSkillId);

    }


}