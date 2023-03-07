package com.clairvoyant.services.skillmatrix.graphql.resolver.query;

import com.clairvoyant.services.skillmatrix.dto.AssessmentDto;
import com.clairvoyant.services.skillmatrix.dto.SkillCategoryResponse;
import com.clairvoyant.services.skillmatrix.service.SkillAssessmentService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
