
package com.clairvoyant.services.skillmatrix.graphql.resolver.mutation;


import com.clairvoyant.services.skillmatrix.enums.Status;
import com.clairvoyant.services.skillmatrix.model.Category;
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
    public Category addOrUpdateCategory(String catName, String description) {
        Category category = Category.builder()
                .catName(catName)
                .description(description)
                .build();

        return categoryService.addOrUpdateCategory(category);
    }

    /**
     * deleteCategory is used to delete the Category
     */
    public Status deleteCategory(String categoryId) {
        categoryService.deleteCategory(categoryId);
        return Status.SUCCESS;
    }
}

