package com.clairvoyant.clarise.controller;

import com.clairvoyant.clarise.dto.AssessmentDto;
import com.clairvoyant.clarise.dto.SkillCategoryResponse;
import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.service.SkillAssessmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/skillassessment")
public class SkillAssessmentController {

    private static final Logger LOGGER = LogManager.getLogger(SkillAssessmentController.class);

    @Autowired
    private SkillAssessmentService skillAssessmentService;

    /**
     * Get SkillAssessment details by category ids.
     *
     * @param category_ids List of category_ids
     * @return SkillCategoryResponse.
     */
    @GetMapping
    public SkillCategoryResponse getSkillAssessmentDetails(@RequestBody List<String> category_ids) {
        return skillAssessmentService.getSkillAssessmentDetails(category_ids);
    }

    /**
     * Save or Update Skill Assessment details.
     *
     * @param assessmentDto assessmentDto
     * @return Status.
     */
    @PostMapping("/saveSkillAssessmentDetails")
    public Status saveSkillAssessmentDetails(@RequestBody AssessmentDto assessmentDto){
        skillAssessmentService.saveSkillAssessmentDetails(assessmentDto);

        return Status.SUCCESS;
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
