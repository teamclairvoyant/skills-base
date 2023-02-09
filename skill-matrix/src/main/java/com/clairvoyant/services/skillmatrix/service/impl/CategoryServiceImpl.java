package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.dto.CategoryDto;
import com.clairvoyant.services.skillmatrix.exceptions.DuplicateNameException;
import com.clairvoyant.services.skillmatrix.exceptions.ResourceNotFoundException;
import com.clairvoyant.services.skillmatrix.model.Category;
import com.clairvoyant.services.skillmatrix.repository.CategoryRepository;
import com.clairvoyant.services.skillmatrix.service.CategoryService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto addOrUpdateCategory(CategoryDto categoryDto) {
        Category category = null;
        try {
            if (StringUtils.hasText(categoryDto.getId())) {
                Optional<Category> dbCategory = categoryRepository.findById(categoryDto.getId());
                if (dbCategory.isPresent()) {
                    if (StringUtils.hasLength(categoryDto.getCatName())) {
                        dbCategory.get().setCatName(categoryDto.getCatName());
                    }

                    if (StringUtils.hasLength(categoryDto.getDescription())) {
                        dbCategory.get().setDescription(categoryDto.getDescription());
                    }

                    category = dbCategory.get();
                } else {
                    throw new ResourceNotFoundException("Category with id " + categoryDto.getId() + " not found");
                }
            } else {
                category = modelMapper.map(categoryDto, Category.class);
                category.setActive(true);
            }

            if (Objects.nonNull(category)) {
                category = categoryRepository.save(category);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateNameException("Category with name " + categoryDto.getCatName() + " already exists");
        }

        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto findCategory(String categoryId) {
        Optional<Category> dbCategory = categoryRepository.findById(categoryId);
        if (dbCategory.isEmpty()) {
            throw new ResourceNotFoundException("Category with id " + categoryId + " not found");
        }
        return modelMapper.map(dbCategory.get(), CategoryDto.class);
    }

    @Override
    public void deleteCategory(String categoryId) {
        Optional<Category> dbCategory = categoryRepository.findById(categoryId);
        if (dbCategory.isPresent()) {
            Category category = dbCategory.get();
            category.setActive(false);
            categoryRepository.save(category);
        } else {
            throw new ResourceNotFoundException("Category with id " + categoryId + " not found");
        }
    }
}
