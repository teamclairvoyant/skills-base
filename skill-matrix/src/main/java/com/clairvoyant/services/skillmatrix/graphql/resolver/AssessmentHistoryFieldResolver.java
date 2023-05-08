package com.clairvoyant.services.skillmatrix.graphql.resolver;

import com.clairvoyant.services.skillmatrix.dto.AssessmentHistory;
import com.clairvoyant.services.skillmatrix.dto.AssessmentHistoryResponse;
import com.clairvoyant.services.skillmatrix.model.Category;
import com.clairvoyant.services.skillmatrix.model.Skill;
import com.clairvoyant.services.skillmatrix.model.SkillCategory;
import com.clairvoyant.services.skillmatrix.repository.CategoryRepository;
import com.clairvoyant.services.skillmatrix.repository.SkillRepository;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssessmentHistoryFieldResolver implements GraphQLResolver<AssessmentHistoryResponse> {

    public List<AssessmentHistory> assessmentHistoryList(AssessmentHistoryResponse assessmentHistoryResponse) {
        return assessmentHistoryResponse.getAssessmentHistoryList();
    }
}
