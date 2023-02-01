package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.model.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> findAll();

    public Category addOrUpdateCategory(Category category);

    public Category findCategory(String categoryId);

    public void deleteCategory(String categoryId);
}
