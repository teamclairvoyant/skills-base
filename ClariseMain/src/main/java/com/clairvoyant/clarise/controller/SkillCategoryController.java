package com.clairvoyant.clarise.controller;

import com.clairvoyant.clarise.dto.SkillCategoryDto;
import com.clairvoyant.clarise.model.Skill;
import com.clairvoyant.clarise.model.SkillCategory;
import com.clairvoyant.clarise.service.SkillCategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/v1/skillcategory")
public class SkillCategoryController {

    private static final Logger LOGGER = LogManager.getLogger(SkillCategoryController.class);

    @Autowired
    private SkillCategoryService skillCategoryService;

    //add or update skillcategory mapping
    @PutMapping
    public List<SkillCategory> addOrUpdateSkillCategory(@RequestBody SkillCategoryDto skillCategoryDto) {

        LOGGER.info(skillCategoryDto);

        return skillCategoryService.addOrUpdateSkillCategory(skillCategoryDto);
    }

    //get skills of a category
    @GetMapping("skills/{categoryId}")
    public List<Skill> getSkills(@PathVariable String categoryId) {

        return skillCategoryService.getSkills(categoryId);
    }
}
