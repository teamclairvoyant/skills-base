
package com.clairvoyant.clarise.graphql;


import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.model.Category;
import com.clairvoyant.clarise.service.CategoryService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CategoryMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private CategoryService categoryService;

    public Category addOrUpdateCategory(String catName, String description, Boolean isActive) {
        Category category = Category.builder()
                .catName(catName)
                .description(description)
                .isActive(isActive)
                .build();

        return categoryService.addOrUpdateCategory(category);
    }
    public Status deleteCategory(String categoryId) {
        categoryService.deleteCategory(categoryId);
        return Status.SUCCESS;
    }
}

