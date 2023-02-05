package com.clairvoyant.services.skillmatrix.controller;

import com.clairvoyant.services.skillmatrix.dto.AssessmentDto;
import com.clairvoyant.services.skillmatrix.dto.SkillCategoryResponse;
import com.clairvoyant.services.skillmatrix.model.SkillAssessment;
import com.clairvoyant.services.skillmatrix.service.SkillAssessmentService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/skillassessment")
public class SkillAssessmentController {

    private static final Logger LOGGER = LogManager.getLogger(SkillAssessmentController.class);

    @Autowired
    private SkillAssessmentService skillAssessmentService;

    /**
     * Get SkillAssessment details by category ids.
     *
     * @param categoryIds List of categoryIds
     * @return SkillCategoryResponse.
     */
    @GetMapping
    public SkillCategoryResponse getSkillAssessmentDetails(@RequestBody List<String> categoryIds) {
        return skillAssessmentService.getSkillAssessmentDetails(categoryIds);
    }

    /**
     * Save or Update Skill Assessment details.
     *
     * @param assessmentDto assessmentDto
     * @return Status.
     */
    @PostMapping("/saveSkillAssessmentDetails")
    public List<SkillAssessment> saveSkillAssessmentDetails(@RequestBody AssessmentDto assessmentDto) {
        return skillAssessmentService.saveSkillAssessmentDetails(assessmentDto);
    }

    /**
     * Get saved SkillAssessment details by assessmentId.
     *
     * @param assessmentId assessmentId
     * @return AssessmentDto object.
     */
    @GetMapping("/getSavedSkillAssessmentDetails/{assessmentId}")
    public AssessmentDto getSavedSkillAssessmentDetails(@PathVariable String assessmentId) {
        return skillAssessmentService.getSavedSkillAssessmentDetails(assessmentId);

    }
}
