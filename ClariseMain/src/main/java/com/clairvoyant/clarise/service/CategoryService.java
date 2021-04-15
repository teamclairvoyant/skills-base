package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.model.Category;
import com.clairvoyant.clarise.model.EmployeeSkill;
import com.clairvoyant.clarise.model.Skill;

import java.util.List;

public interface CategoryService {

    public List<Category> findAll();

    public void save(Category theCategory);

    public void deleteCategrory(String CatId);
}
