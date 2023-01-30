package com.clairvoyant.clarise.graphql.resolver;

import com.clairvoyant.clarise.dto.SkillCategoryResponse;
import com.clairvoyant.clarise.model.Assessment;
import com.clairvoyant.clarise.model.SkillAssessment;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class AssessmentFieldResolver implements GraphQLResolver<SkillAssessment> {

    public Assessment assessment(SkillAssessment skillAssessment) {
        return skillAssessment.getAssessment();
    }
}
