package com.clairvoyant.clarise.graphql.resolver;

import com.clairvoyant.clarise.model.SkillAssessment;
import com.clairvoyant.clarise.model.SkillCategory;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class SkillAssessmentSkillCategoryFieldResolver implements GraphQLResolver<SkillAssessment> {

    public SkillCategory skillCategory(SkillAssessment skillAssessment) {
        return skillAssessment.getSkillCategory();
    }
}
