package com.clairvoyant.clarise.graphql.resolver.mutation;

import com.clairvoyant.clarise.dto.AssessmentDto;
import com.clairvoyant.clarise.dto.SkillCategoryDto;
import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.model.SkillAssessment;
import com.clairvoyant.clarise.model.SkillCategory;
import com.clairvoyant.clarise.service.SkillAssessmentService;
import com.clairvoyant.clarise.service.SkillCategoryService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class SkillAssessmentMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private SkillAssessmentService skillAssessmentService;

    public List<SkillAssessment> saveSkillAssessmentDetails(AssessmentDto assessmentDto) {
        return skillAssessmentService.saveSkillAssessmentDetails(assessmentDto);
    }
}
