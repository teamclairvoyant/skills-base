package com.clairvoyant.services.skillmatrix.controller;

import com.clairvoyant.services.skillmatrix.enums.Status;
import com.clairvoyant.services.skillmatrix.model.Category;
import com.clairvoyant.services.skillmatrix.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    private static final Logger LOGGER = LogManager.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    //get all categories
    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    //add or update category to category table
    @PutMapping
    public Category addOrUpdateCategory(@RequestBody Category category) {

        LOGGER.info(category);

        return categoryService.addOrUpdateCategory(category);
    }

    //get category
    @GetMapping("/{categoryId}")
    public Category find(@PathVariable String categoryId){

        return categoryService.findCategory(categoryId);
    }

    //delete category
    @DeleteMapping("/{categoryId}")
    public Status deleteCategory(@PathVariable String categoryId) {

        LOGGER.info("deleteCategory of category Id :" + categoryId);

        categoryService.deleteCategory(categoryId);

        return Status.SUCCESS;
    }
}
