package com.clairvoyant.services.skillmatrix.controller;

import com.clairvoyant.services.skillmatrix.dto.AssessmentHistoryResponse;
import com.clairvoyant.services.skillmatrix.service.AssessmentHistoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/assessmenthistory")
public class AssessmentHistoryController {

    @Autowired
    private AssessmentHistoryService assessmentHistoryService;

    @GetMapping("/{userId}/{assessmentId}")
    public AssessmentHistoryResponse getAssessmentHistory(@PathVariable String userId, @PathVariable String assessmentId) {
        return assessmentHistoryService.getAssessmentHistory(userId, assessmentId);
    }
}
