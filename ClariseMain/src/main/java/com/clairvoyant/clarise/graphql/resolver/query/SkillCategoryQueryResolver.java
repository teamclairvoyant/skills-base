package com.clairvoyant.clarise.graphql.resolver.query;

import com.clairvoyant.clarise.model.Category;
import com.clairvoyant.clarise.model.Skill;
import com.clairvoyant.clarise.service.SkillCategoryService;
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
