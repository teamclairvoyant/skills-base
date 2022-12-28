package com.clairvoyant.clarise.service.impl;

import com.clairvoyant.clarise.model.Category;
import com.clairvoyant.clarise.repository.CategoryRepository;
import com.clairvoyant.clarise.service.CategoryService;
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
