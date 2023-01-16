package com.clairvoyant.clarise.graphql;

import com.clairvoyant.clarise.dto.SkillCategoryDto;
import com.clairvoyant.clarise.model.Category;
import com.clairvoyant.clarise.model.SkillCategory;
import com.clairvoyant.clarise.service.SkillCategoryService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillCategoryMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private SkillCategoryService skillCategoryService;


    public List<SkillCategory> addOrUpdateSkillCategory(SkillCategoryDto skillCategoryDto) {
        return skillCategoryService.addOrUpdateSkillCategory(skillCategoryDto);
    }
}
