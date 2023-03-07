package com.clairvoyant.services.skillmatrix.graphql.resolver;

import com.clairvoyant.services.skillmatrix.model.SkillAssessment;
import com.clairvoyant.services.skillmatrix.model.SkillCategory;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class SkillAssessmentSkillCategoryFieldResolver implements GraphQLResolver<SkillAssessment> {

    public SkillCategory skillCategory(SkillAssessment skillAssessment) {
        return skillAssessment.getSkillCategory();
    }
}
