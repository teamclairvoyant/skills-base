package com.clairvoyant.services.skillmatrix.repository;

import com.clairvoyant.services.skillmatrix.model.SkillCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillCategoryRepository extends JpaRepository<SkillCategory, String> {

    List<SkillCategory> findByCategoryId(String categoryId);
    List<SkillCategory> findByCategoryIdAndIsActive(String categoryId, boolean isActive);
}
