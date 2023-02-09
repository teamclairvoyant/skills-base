package com.clairvoyant.services.skillmatrix.graphql.resolver.mutation;

import com.clairvoyant.services.skillmatrix.dto.CategoryDto;
import com.clairvoyant.services.skillmatrix.enums.Status;
import com.clairvoyant.services.skillmatrix.service.CategoryService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CategoryMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private CategoryService categoryService;

    /**
     * addOrUpdateCategory is used to add or update the Category
     */
    public CategoryDto addOrUpdateCategory(CategoryDto categoryDto) {
        return categoryService.addOrUpdateCategory(categoryDto);
    }

    /**
     * deleteCategory is used to delete the Category
     */
    public Status deleteCategory(String categoryId) {
        categoryService.deleteCategory(categoryId);
        return Status.SUCCESS;
    }
}

