package com.clairvoyant.clarise.graphql.resolver.query;

import com.clairvoyant.clarise.model.Category;
import com.clairvoyant.clarise.service.CategoryService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class CategoryQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private CategoryService categoryService;

    public List<Category> getCategories() {
        return categoryService.findAll();
    }

    public Category findCategoryById(String categoryId) {
        return categoryService.findCategory(categoryId);
    }
}
