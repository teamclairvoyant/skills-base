package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.dto.SkillCategoryDto;
import com.clairvoyant.services.skillmatrix.exceptions.ResourceNotFoundException;
import com.clairvoyant.services.skillmatrix.model.Category;
import com.clairvoyant.services.skillmatrix.model.Skill;
import com.clairvoyant.services.skillmatrix.model.SkillCategory;
import com.clairvoyant.services.skillmatrix.repository.SkillCategoryRepository;
import com.clairvoyant.services.skillmatrix.service.SkillCategoryService;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillCategoryServiceImpl implements SkillCategoryService {

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    @Override
    public List<SkillCategory> addOrUpdateSkillCategory(SkillCategoryDto skillCategoryDto) {
        List<SkillCategory> skillCategories = skillCategoryRepository.findByCategoryId(skillCategoryDto.getCategoryId());

        List<SkillCategory> skillCategoryList = new ArrayList<>();
        if (skillCategories.isEmpty()) {
            //Insert skills for the first time
            newSkillMapping(skillCategoryDto.getCategoryId(), skillCategoryDto.getSkillIds(), skillCategoryList);
        } else {
            List<String> dbSkillIds = skillCategories.stream().map(skillCategory -> skillCategory.getSkill().getId()).collect(Collectors.toList());
            List<String> dbActiveSkillIds = skillCategories.stream().filter(SkillCategory::isActive)
                .map(skillCategory -> skillCategory.getSkill().getId()).collect(Collectors.toList());

            //Insert new Skill for the category -- create
            List<String> newSkillIds = new ArrayList<>(Sets.difference(Sets.newHashSet(skillCategoryDto.getSkillIds()), Sets.newHashSet(dbSkillIds)));
            newSkillMapping(skillCategoryDto.getCategoryId(), newSkillIds, skillCategoryList);

            //removing newly inserted skillIds from request so that newly inserted ids will not come while updating in the below
            skillCategoryDto.getSkillIds().removeIf(newSkillIds::contains);


            //update to inactive for existing mappings -- delete
            List<String> deletedSkillIds = new ArrayList<>(Sets.difference(Sets.newHashSet(dbActiveSkillIds), Sets.newHashSet(skillCategoryDto.getSkillIds())));
            for (String skillId : deletedSkillIds) {
                Optional<SkillCategory> optSkillCategory =
                    skillCategories.stream().filter(dbSkillCategory -> skillId.equals(dbSkillCategory.getSkill().getId())).findFirst();
                SkillCategory skillCategory;
                if (optSkillCategory.isPresent()) {
                    skillCategory = optSkillCategory.get();
                } else {
                    throw new ResourceNotFoundException("SkillCategory mapping does not exist for Skill ID : " + skillId);
                }
                skillCategory.setActive(false);
                skillCategoryList.add(skillCategory);
            }

            //update existing inactive mapping to true -- update
            List<String> updateSkillIds = new ArrayList<>(Sets.difference(Sets.newHashSet(skillCategoryDto.getSkillIds()), Sets.newHashSet(dbActiveSkillIds)));
            for (String skillId : updateSkillIds) {
                Optional<SkillCategory> optSkillCategory =
                    skillCategories.stream().filter(dbSkillCategory -> skillId.equals(dbSkillCategory.getSkill().getId())).findFirst();

                SkillCategory skillCategory;
                if (optSkillCategory.isPresent()) {
                    skillCategory = optSkillCategory.get();
                } else {
                    throw new ResourceNotFoundException("Skill Category Mapping does not exist for skill id : " + skillId);
                }

                skillCategory.setActive(true);
                skillCategoryList.add(skillCategory);
            }

        }
        if (skillCategoryList.isEmpty()) {
            return skillCategoryRepository.saveAll(skillCategoryList);
        }
        return List.of();
    }

    @Override
    public List<Skill> getSkills(String categoryId) {

        List<SkillCategory> skillCategories = skillCategoryRepository.findByCategoryIdAndIsActive(categoryId, true);
        return skillCategories.stream().map(SkillCategory::getSkill).collect(Collectors.toList());
    }

    private void newSkillMapping(String categoryId, List<String> newSkillIds, List<SkillCategory> skillCategoryList) {
        for (String skillId : newSkillIds) {
            var skillCategory = new SkillCategory();
            var category = new Category();
            category.setId(categoryId);
            skillCategory.setCategory(category);
            var skill = new Skill();
            skill.setId(skillId);
            skillCategory.setSkill(skill);
            skillCategory.setActive(true);
            skillCategoryList.add(skillCategory);
        }
    }
}
