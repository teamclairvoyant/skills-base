package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.model.Category;
import com.clairvoyant.clarise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category theCategory) {
        categoryRepository.save(theCategory);
    }

    @Override
    public void deleteCategrory(String CatId) {
        categoryRepository.deleteById(CatId);
    }
}
