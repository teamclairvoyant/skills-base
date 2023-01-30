package com.clairvoyant.clarise.graphql.resolver;

import com.clairvoyant.clarise.model.SkillAssessment;
import com.clairvoyant.clarise.model.SkillsRating;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class SkillsRatingFieldResolver implements GraphQLResolver<SkillAssessment> {

    public SkillsRating skillsRating(SkillAssessment skillAssessment) {
        return skillAssessment.getSkillsRating();
    }
}
