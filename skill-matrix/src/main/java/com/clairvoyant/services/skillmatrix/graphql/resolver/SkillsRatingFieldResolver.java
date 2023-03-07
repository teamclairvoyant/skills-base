package com.clairvoyant.services.skillmatrix.graphql.resolver;

import com.clairvoyant.services.skillmatrix.model.SkillAssessment;
import com.clairvoyant.services.skillmatrix.model.SkillsRating;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class SkillsRatingFieldResolver implements GraphQLResolver<SkillAssessment> {

    public SkillsRating skillsRating(SkillAssessment skillAssessment) {
        return skillAssessment.getSkillsRating();
    }
}
