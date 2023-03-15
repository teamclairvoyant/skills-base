package com.clairvoyant.services.skillmatrix.graphql.resolver;

import com.clairvoyant.services.skillmatrix.dto.CategoryList;
import com.clairvoyant.services.skillmatrix.dto.SkillCategoryResponse;
import com.clairvoyant.services.skillmatrix.repository.CategoryRepository;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryListFieldResolver implements GraphQLResolver<SkillCategoryResponse> {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryList> categoryList(SkillCategoryResponse skillCategoryResponse) {
        return skillCategoryResponse.getCategoryList();
    }
}
