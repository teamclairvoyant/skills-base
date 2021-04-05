package com.prd.skillbase.service;


import com.prd.skillbase.model.EmployeeSkill;
import com.prd.skillbase.model.Skill;

import java.util.List;

public interface SkillService {

   public List<Skill> findAll();

   public void save(Skill theSkill);

   public void updateSkill(String empId, String skillId , EmployeeSkill employeeSkill);

   public void deleteSkills(String employeeSkillId);


}













