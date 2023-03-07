package com.clairvoyant.services.skillmatrix.graphql.resolver.mutation;

import com.clairvoyant.services.skillmatrix.dto.AssessmentDto;
import com.clairvoyant.services.skillmatrix.model.SkillAssessment;
import com.clairvoyant.services.skillmatrix.service.SkillAssessmentService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SkillAssessmentMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private SkillAssessmentService skillAssessmentService;

    public List<SkillAssessment> saveSkillAssessmentDetails(AssessmentDto assessmentDto) {
        return skillAssessmentService.saveSkillAssessmentDetails(assessmentDto);
    }
}
