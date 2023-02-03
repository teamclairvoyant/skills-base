package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.model.Category;
import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category addOrUpdateCategory(Category category);

    Category findCategory(String categoryId);

    void deleteCategory(String categoryId);
}
