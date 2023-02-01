package com.clairvoyant.services.skillmatrix.graphql.resolver.query;

import com.clairvoyant.services.skillmatrix.model.Skill;
import com.clairvoyant.services.skillmatrix.service.SkillCategoryService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillCategoryQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private SkillCategoryService skillCategoryService;

    public List<Skill> getSkills(String categoryId) {
        return skillCategoryService.getSkills(categoryId);
    }
}
