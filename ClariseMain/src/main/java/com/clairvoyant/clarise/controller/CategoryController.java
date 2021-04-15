package com.clairvoyant.clarise.controller;

import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.model.Category;
import com.clairvoyant.clarise.model.Skill;
import com.clairvoyant.clarise.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private static final Logger LOGGER = LogManager.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public List<Category> findAll(){

        return categoryService.findAll();
    }

    @PostMapping("")
    public Status addCategory(@RequestBody Category theCategory) {

        LOGGER.info(theCategory);

        categoryService.save(theCategory);

        return Status.SUCCESS;
    }
}
