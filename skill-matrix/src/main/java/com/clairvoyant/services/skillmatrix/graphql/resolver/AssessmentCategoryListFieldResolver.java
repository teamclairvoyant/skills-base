package com.clairvoyant.services.skillmatrix.graphql.resolver;

import com.clairvoyant.services.skillmatrix.dto.AssessmentDto;
import com.clairvoyant.services.skillmatrix.dto.CategoryList;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AssessmentCategoryListFieldResolver implements GraphQLResolver<AssessmentDto> {

    public List<CategoryList> categoryList(AssessmentDto assessmentDto) {
        return assessmentDto.getCategoryList();
    }
}
