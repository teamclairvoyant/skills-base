package com.clairvoyant.clarise.controller;


import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.model.Skill;
import com.clairvoyant.clarise.service.SkillService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

//skill controller
@RestController
@RequestMapping("/v1/skills")
public class SkillController {

    private static final Logger LOGGER = LogManager.getLogger(SkillController.class);

    @Autowired
    private SkillService skillService;

    //get all skills
    @GetMapping
    public List<Skill> findAll(){

        return skillService.findAll();
    }

    //add or update skill to skills table
    @PutMapping
    public Skill addOrUpdateSkill(@RequestBody Skill skill) {

        LOGGER.info(skill);

        return skillService.addOrUpdateSkill(skill);
    }

    /*//add skill to specific employee
    @PostMapping("/{empId}/{skillId}")
    public Status updateSkill(@PathVariable String empId , @PathVariable String skillId, @RequestBody EmployeeSkill employeeSkill) {

        LOGGER.info("Employee Id :"+empId+" Skill Id"+skillId+" employeeSkill :"+employeeSkill);

        skillService.updateSkill(empId , skillId , employeeSkill);

        return Status.SUCCESS;
    }*/

    //get skill
    @GetMapping("/{skillId}")
    public Skill find(@PathVariable String skillId){

        return skillService.findSkill(skillId);
    }

    //delete skill
    @DeleteMapping("/{skillId}")
    public Status deleteSkill(@PathVariable String skillId) {

        LOGGER.info("deleteSkill of skill Id :" + skillId);

        skillService.deleteSkill(skillId);

        return Status.SUCCESS;
    }

}

