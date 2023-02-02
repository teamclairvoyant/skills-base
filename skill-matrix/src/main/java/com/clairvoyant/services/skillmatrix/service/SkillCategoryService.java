package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.dto.SkillCategoryDto;
import com.clairvoyant.services.skillmatrix.model.Skill;
import com.clairvoyant.services.skillmatrix.model.SkillCategory;
import java.util.List;

public interface SkillCategoryService {

    List<SkillCategory> addOrUpdateSkillCategory(SkillCategoryDto skillCategoryDto);

    List<Skill> getSkills(String categoryId);
}
