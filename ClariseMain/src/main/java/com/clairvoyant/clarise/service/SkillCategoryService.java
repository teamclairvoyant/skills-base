package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.dto.SkillCategoryDto;
import com.clairvoyant.clarise.model.Skill;
import com.clairvoyant.clarise.model.SkillCategory;

import java.util.List;

public interface SkillCategoryService {

    List<SkillCategory> addOrUpdateSkillCategory(SkillCategoryDto skillCategoryDto);
    List<Skill> getSkills(String categoryId);
}
