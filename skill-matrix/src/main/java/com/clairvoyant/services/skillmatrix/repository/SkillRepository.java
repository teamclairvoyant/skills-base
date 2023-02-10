package com.clairvoyant.services.skillmatrix.repository;


import com.clairvoyant.services.skillmatrix.model.Skill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, String> {
    List<Skill> findByIsActive(Boolean isActive);
}
