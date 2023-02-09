package com.clairvoyant.services.skillmatrix.repository;


import com.clairvoyant.services.skillmatrix.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, String> {
    List<Skill> findByIsActive(Boolean isActive);
}
