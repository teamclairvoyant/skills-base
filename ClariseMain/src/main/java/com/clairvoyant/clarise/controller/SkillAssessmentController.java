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

    //get skills of a category
    @GetMapping
    public SkillCategoryResponse getSkillAssessmentDetails(@RequestBody List<String> category_ids) {
        return skillAssessmentService.getSkillAssessmentDetails(category_ids);
    }

    @PostMapping("/saveSkillAssesementdetails")
    public Status saveSkillAssesementdetails(@RequestBody AssessmentDto assessmentDto){
        skillAssessmentService.saveSkillAssessmentdetails(assessmentDto);

        return Status.SUCCESS;
    }

    @GetMapping("/getSavedSkillAssessmentdetails/{assessmentId}")
    public AssessmentDto getSavedSkillAssessmentdetails(@PathVariable String assessmentId) {
        return skillAssessmentService.getSavedSkillAssessmentdetails(assessmentId);

    }
}
