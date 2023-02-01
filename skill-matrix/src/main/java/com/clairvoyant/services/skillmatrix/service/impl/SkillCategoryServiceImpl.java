package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.dto.SkillCategoryDto;
import com.clairvoyant.services.skillmatrix.model.Category;
import com.clairvoyant.services.skillmatrix.model.Skill;
import com.clairvoyant.services.skillmatrix.model.SkillCategory;
import com.clairvoyant.services.skillmatrix.repository.SkillCategoryRepository;
import com.clairvoyant.services.skillmatrix.service.SkillCategoryService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SkillCategoryServiceImpl implements SkillCategoryService {

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    @Override
    public List<SkillCategory> addOrUpdateSkillCategory(SkillCategoryDto skillCategoryDto) {
        List<SkillCategory> skillCategories = skillCategoryRepository.findByCategoryId(skillCategoryDto.getCategoryId());

        List<SkillCategory> skillCategoryList = new ArrayList<>();
        if (Objects.isNull(skillCategories) || skillCategories.size() == 0 ) {
            //Insert skills for the first time
            newSkillMapping(skillCategoryDto.getCategoryId(), skillCategoryDto.getSkillIds(), skillCategoryList);
        }
        else {
            List<String> dbSkillIds = skillCategories.stream().map(skillCategory -> skillCategory.getSkill().getId()).collect(Collectors.toList());
            List<String> dbActiveSkillIds = skillCategories.stream().filter(skillCategory -> skillCategory.isActive())
                    .map(skillCategory -> skillCategory.getSkill().getId()).collect(Collectors.toList());

            //Insert new Skill for the category -- create
            List<String> newSkillIds = new ArrayList<>(Sets.difference(Sets.newHashSet(skillCategoryDto.getSkillIds()), Sets.newHashSet(dbSkillIds)));
            newSkillMapping(skillCategoryDto.getCategoryId(), newSkillIds, skillCategoryList);

            //removing newly inserted skillIds from request so that newly inserted ids will not come while updating in the below
            skillCategoryDto.getSkillIds().removeIf(s -> newSkillIds.contains(s));


            //update to inactive for existing mappings -- delete
            List<String> deletedSkillIds = new ArrayList<>(Sets.difference(Sets.newHashSet(dbActiveSkillIds), Sets.newHashSet(skillCategoryDto.getSkillIds())));
            for (String skillId : deletedSkillIds) {
                SkillCategory skillCategory = skillCategories.stream().filter(dbSkillCategory -> skillId.equals(dbSkillCategory.getSkill().getId())).findFirst().get();
                skillCategory.setActive(false);
                skillCategoryList.add(skillCategory);
            }

            //update existing inactive mapping to true -- update
            List<String> updateSkillIds = new ArrayList<>(Sets.difference(Sets.newHashSet(skillCategoryDto.getSkillIds()), Sets.newHashSet(dbActiveSkillIds)));
            for (String skillId : updateSkillIds) {
                SkillCategory skillCategory = skillCategories.stream().filter(dbSkillCategory -> skillId.equals(dbSkillCategory.getSkill().getId())).findFirst().get();
                skillCategory.setActive(true);
                skillCategoryList.add(skillCategory);
            }

        }
        if (Objects.nonNull(skillCategoryList) && skillCategoryList.size() > 0) {
            return skillCategoryRepository.saveAll(skillCategoryList);
        }
        return null;
    }

    @Override
    public List<Skill> getSkills(String categoryId) {

        List<SkillCategory> skillCategories = skillCategoryRepository.findByCategoryIdAndIsActive(categoryId, true);
        return skillCategories.stream().map(skillCategory -> skillCategory.getSkill()).collect(Collectors.toList());
    }

    private void newSkillMapping(String categoryId, List<String> newSkillIds, List<SkillCategory> skillCategoryList) {
        for (String skillId : newSkillIds) {
            SkillCategory skillCategory = new SkillCategory();
            Category category = new Category();
            category.setId(categoryId);
            skillCategory.setCategory(category);
            Skill skill = new Skill();
            skill.setId(skillId);
            skillCategory.setSkill(skill);
            skillCategory.setActive(true);
            skillCategoryList.add(skillCategory);
        }
    }
}
