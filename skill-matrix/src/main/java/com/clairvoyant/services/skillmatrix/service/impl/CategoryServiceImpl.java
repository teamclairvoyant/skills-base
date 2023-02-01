package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.model.Category;
import com.clairvoyant.services.skillmatrix.repository.CategoryRepository;
import com.clairvoyant.services.skillmatrix.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addOrUpdateCategory(Category category) {
        category.setActive(true);
        return categoryRepository.save(category);
    }

    @Override
    public Category findCategory(String categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    @Override
    public void deleteCategory(String categoryId) {
        Optional<Category> optCategory = categoryRepository.findById(categoryId);
        if (optCategory.isPresent()) {
            Category category = optCategory.get();
            category.setActive(false);
            categoryRepository.save(category);
        }
    }
}
