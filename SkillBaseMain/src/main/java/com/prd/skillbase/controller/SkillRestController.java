package com.prd.skillbase.controller;


import com.prd.skillbase.enums.Status;
import com.prd.skillbase.model.EmployeeSkill;
import com.prd.skillbase.model.Skill;
import com.prd.skillbase.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//skill controller
@RestController
@RequestMapping("/skills")
public class SkillRestController {

    @Autowired
    private SkillService skillService;

    //get all required skills
    @GetMapping("")
    public List<Skill> findAll() {
        return skillService.findAll();
    }


    //add new skills to skill table
    @PostMapping("")
    public Status addSkill(@RequestBody Skill theSkill) {

        skillService.save(theSkill);

        return Status.SUCCESS;
    }

    //add skill to specific employee
    @PostMapping("/{empId}/{skillId}")
    public Status updateSkill(@PathVariable String empId , @PathVariable String skillId, @RequestBody EmployeeSkill employeeSkill) {


        skillService.updateSkill(empId , skillId , employeeSkill);

        return Status.SUCCESS;
    }

    //delete skills from specific employee
    @DeleteMapping("/{employeeSkillId}")
    public Status deleteSkills(@PathVariable String employeeSkillId) {

       skillService.deleteSkills(employeeSkillId);

       return Status.SUCCESS;
    }

}

