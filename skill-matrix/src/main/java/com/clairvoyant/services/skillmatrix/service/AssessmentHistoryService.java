package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.dto.AssessmentHistoryResponse;
import java.util.List;

public interface AssessmentHistoryService {
    AssessmentHistoryResponse getAssessmentHistory(String userId, String assessmentId);
}
