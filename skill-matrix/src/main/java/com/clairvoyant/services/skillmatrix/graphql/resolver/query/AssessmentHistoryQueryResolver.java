package com.clairvoyant.services.skillmatrix.graphql.resolver.query;

import com.clairvoyant.services.skillmatrix.dto.AssessmentHistoryResponse;
import com.clairvoyant.services.skillmatrix.service.AssessmentHistoryService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssessmentHistoryQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private AssessmentHistoryService assessmentHistoryService;


    public AssessmentHistoryResponse getAssessmentHistory(String userId, String assessmentId) {
        return assessmentHistoryService.getAssessmentHistory(userId, assessmentId);
    }

}
