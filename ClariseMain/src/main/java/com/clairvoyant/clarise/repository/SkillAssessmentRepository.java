package com.clairvoyant.clarise.repository;

import com.clairvoyant.clarise.model.Category;
import com.clairvoyant.clarise.model.SkillAssessment;
import com.clairvoyant.clarise.model.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillAssessmentRepository extends JpaRepository<SkillAssessment,String> {

    SkillAssessment findBySkillCategoryId(String skillCategoryId);

    List<SkillAssessment> findByAssessmentId(String skillCategoryId);
}
