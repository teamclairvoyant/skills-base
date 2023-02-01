package com.clairvoyant.services.skillmatrix.graphql.resolver.mutation;

import com.clairvoyant.services.skillmatrix.dto.SkillCategoryDto;
import com.clairvoyant.services.skillmatrix.model.SkillCategory;
import com.clairvoyant.services.skillmatrix.service.SkillCategoryService;
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
