package com.clairvoyant.clarise.graphql.resolver;

import com.clairvoyant.clarise.dto.CategoryList;
import com.clairvoyant.clarise.dto.SkillCategoryResponse;
import com.clairvoyant.clarise.repository.CategoryRepository;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryListFieldResolver implements GraphQLResolver<SkillCategoryResponse> {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryList> CategoryList(SkillCategoryResponse skillCategoryResponse) {
        return skillCategoryResponse.getCategoryList();
    }
}
