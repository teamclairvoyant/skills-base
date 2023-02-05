package com.clairvoyant.services.skillmatrix.graphql.resolver;

import com.clairvoyant.services.skillmatrix.dto.AssessmentCategoryList;
import com.clairvoyant.services.skillmatrix.dto.AssessmentDto;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AssessmentCategoryListFieldResolver implements GraphQLResolver<AssessmentDto> {

    public List<AssessmentCategoryList> assessmentCategoryList(AssessmentDto assessmentDto) {
        return assessmentDto.getAssessmentCategoryList();
    }
}
