package com.clairvoyant.clarise.graphql.resolver;

import com.clairvoyant.clarise.dto.AssessmentCategoryList;
import com.clairvoyant.clarise.dto.AssessmentDto;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssessmentCategoryListFieldResolver implements GraphQLResolver<AssessmentDto> {

    public List<AssessmentCategoryList> assessmentCategoryList(AssessmentDto assessmentDto) {
        return assessmentDto.getAssessmentCategoryList();
    }
}
