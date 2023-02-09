package com.clairvoyant.services.skillmatrix.graphql.resolver.query;

import com.clairvoyant.services.skillmatrix.dto.CategoryDto;
import com.clairvoyant.services.skillmatrix.service.CategoryService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class CategoryQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private CategoryService categoryService;


    /**
     * getCategories is used to get all the categories
     */
    public List<CategoryDto> getCategories() {
        return categoryService.findAll();
    }

    /**
     * findCategoryById is used to get the category based on the passed categoryId
     */
    public CategoryDto findCategoryById(String categoryId) {
        return categoryService.findCategory(categoryId);
    }
}
