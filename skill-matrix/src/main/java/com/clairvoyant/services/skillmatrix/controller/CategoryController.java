package com.clairvoyant.services.skillmatrix.controller;

import com.clairvoyant.services.skillmatrix.dto.CategoryDto;
import com.clairvoyant.services.skillmatrix.enums.Status;
import com.clairvoyant.services.skillmatrix.service.CategoryService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    private static final Logger LOGGER = LogManager.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    //get all categories
    @GetMapping
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    //add or update category to category table
    @PutMapping
    public CategoryDto addOrUpdateCategory(@RequestBody CategoryDto categoryDto) {

        LOGGER.info("addOrUpdateCategory request: " + categoryDto);

        return categoryService.addOrUpdateCategory(categoryDto);
    }

    //get category
    @GetMapping("/{categoryId}")
    public CategoryDto find(@PathVariable String categoryId) {

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
