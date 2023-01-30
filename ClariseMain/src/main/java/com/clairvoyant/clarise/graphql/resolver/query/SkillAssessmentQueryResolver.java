package com.clairvoyant.clarise.graphql.resolver.query;

import com.clairvoyant.clarise.dto.AssessmentDto;
import com.clairvoyant.clarise.dto.SkillCategoryResponse;
import com.clairvoyant.clarise.service.SkillAssessmentService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillAssessmentQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private SkillAssessmentService skillAssessmentService;

    public SkillCategoryResponse getSkillAssessmentDetails(List<String> categoryIds) {
        return skillAssessmentService.getSkillAssessmentDetails(categoryIds);
    }

    public AssessmentDto getSavedSkillAssessmentDetails(String assessmentId) {
        return skillAssessmentService.getSavedSkillAssessmentDetails(assessmentId);
    }
}
