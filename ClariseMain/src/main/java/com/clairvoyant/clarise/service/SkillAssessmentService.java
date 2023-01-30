package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.dto.AssessmentDto;
import com.clairvoyant.clarise.dto.SkillCategoryResponse;
import com.clairvoyant.clarise.model.SkillAssessment;

import java.util.List;

public interface SkillAssessmentService {
    SkillCategoryResponse getSkillAssessmentDetails(List<String> categoryIds);

    List<SkillAssessment> saveSkillAssessmentDetails(AssessmentDto assessmentDto);

    AssessmentDto getSavedSkillAssessmentDetails(String assessmentId);
}
