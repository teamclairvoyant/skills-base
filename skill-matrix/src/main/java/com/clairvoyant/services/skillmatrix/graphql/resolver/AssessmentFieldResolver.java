package com.clairvoyant.services.skillmatrix.graphql.resolver;

import com.clairvoyant.services.skillmatrix.model.Assessment;
import com.clairvoyant.services.skillmatrix.model.SkillAssessment;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class AssessmentFieldResolver implements GraphQLResolver<SkillAssessment> {

    public Assessment assessment(SkillAssessment skillAssessment) {
        return skillAssessment.getAssessment();
    }
}
