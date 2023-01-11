package com.clairvoyant.clarise.repository;

import com.clairvoyant.clarise.model.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillCategoryRepository extends JpaRepository<SkillCategory, String> {

    List<SkillCategory> findByCategoryId(String categoryId);
    List<SkillCategory> findByCategoryIdAndIsActive(String categoryId, boolean isActive);
}
