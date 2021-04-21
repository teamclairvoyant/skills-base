package com.clairvoyant.clarise.service;


import com.clairvoyant.clarise.model.Skill;
import com.clairvoyant.clarise.model.EmployeeSkill;

import java.util.List;

public interface SkillService {

   public List<Skill> findAll();

   public void save(Skill theSkill);

   public void updateSkill(String empId, String skillId , EmployeeSkill employeeSkill);

   public void deleteSkills(String empId);


}













