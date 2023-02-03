package com.clairvoyant.services.skillmatrix.controller;

import com.clairvoyant.services.skillmatrix.dto.SkillDto;
import com.clairvoyant.services.skillmatrix.enums.Status;
import com.clairvoyant.services.skillmatrix.service.SkillService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//skill controller
@RestController
@RequestMapping("/v1/skills")
@Validated
public class SkillController {

    private static final Logger LOGGER = LogManager.getLogger(SkillController.class);

    @Autowired
    private SkillService skillService;

    //get all skills
    @GetMapping
    public List<SkillDto> findAll(){
        return skillService.findAll();
    }

    //add or update skill to skills table
    @PutMapping
    public SkillDto addOrUpdateSkill(@RequestBody SkillDto skillDto) {

        LOGGER.info("addOrUpdateSkill request: " + skillDto);

        return skillService.addOrUpdateSkill(skillDto);
    }

    //get skill
    @GetMapping("/{skillId}")
    public SkillDto find(@PathVariable String skillId){
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

