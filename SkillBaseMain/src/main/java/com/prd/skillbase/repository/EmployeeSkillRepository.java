package com.prd.skillbase.repository;

import com.prd.skillbase.model.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill , String> {
    void deleteById(String id);
}
