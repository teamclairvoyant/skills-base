package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.dto.CategoryDto;
import java.util.List;

public interface CategoryService {

    List<CategoryDto> findAll();

    CategoryDto addOrUpdateCategory(CategoryDto categoryDto);

    CategoryDto findCategory(String categoryId);

    void deleteCategory(String categoryId);
}
