package com.clairvoyant.services.skillmatrix.repository;

import com.clairvoyant.services.skillmatrix.model.SkillAssessment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillAssessmentRepository extends JpaRepository<SkillAssessment,String> {

    List<SkillAssessment> findBySkillCategoryId(String skillCategoryId);

    List<SkillAssessment> findByAssessmentId(String skillCategoryId);
}
