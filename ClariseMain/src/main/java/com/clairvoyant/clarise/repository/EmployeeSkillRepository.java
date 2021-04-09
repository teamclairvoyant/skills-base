package com.clairvoyant.clarise.repository;

import com.clairvoyant.clarise.model.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill , String> {
    void deleteById(String id);
}
