package com.clairvoyant.clarise.controller;


import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.model.Skill;
import com.clairvoyant.clarise.model.EmployeeSkill;
import com.clairvoyant.clarise.service.SkillService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

//skill controller
@RestController
@RequestMapping("/api/skills")
//@CrossOrigin(origins = "*")
public class SkillRestController {

    private static final Logger LOGGER = LogManager.getLogger(SkillRestController.class);

    @Autowired
    private SkillService skillService;

    //get all required skills
    @GetMapping("")
    public List<Skill> findAll(){

        return skillService.findAll();
    }


    //add new skills to skill table
    @PostMapping("")
    public Status addSkill(@RequestBody Skill theSkill) {

        LOGGER.info(theSkill);

        skillService.save(theSkill);

        return Status.SUCCESS;
    }

    //add skill to specific employee
    @PostMapping("/{empId}/{skillId}")
    public Status updateSkill(@PathVariable String empId , @PathVariable String skillId, @RequestBody EmployeeSkill employeeSkill) {

        LOGGER.info("Employee Id :"+empId+" Skill Id"+skillId+" employeeSkill :"+employeeSkill);

        skillService.updateSkill(empId , skillId , employeeSkill);

        return Status.SUCCESS;
    }

    //delete skills from specific employee
    @DeleteMapping("/{employeeSkillId}")
    public Status deleteSkills(@PathVariable String employeeSkillId) {

        LOGGER.info("Employee Skill Id :"+employeeSkillId);

       skillService.deleteSkills(employeeSkillId);

       return Status.SUCCESS;
    }

}

