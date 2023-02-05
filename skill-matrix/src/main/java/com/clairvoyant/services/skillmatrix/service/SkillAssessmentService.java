package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.dto.AssessmentDto;
import com.clairvoyant.services.skillmatrix.dto.SkillCategoryResponse;
import com.clairvoyant.services.skillmatrix.model.SkillAssessment;
import java.util.List;

public interface SkillAssessmentService {
    SkillCategoryResponse getSkillAssessmentDetails(List<String> categoryIds);

    List<SkillAssessment> saveSkillAssessmentDetails(AssessmentDto assessmentDto);

    AssessmentDto getSavedSkillAssessmentDetails(String assessmentId);
}
