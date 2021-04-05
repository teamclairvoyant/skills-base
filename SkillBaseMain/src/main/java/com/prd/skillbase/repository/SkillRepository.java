package com.prd.skillbase.repository;


import com.prd.skillbase.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, String> {

   // JDBC-based database access and object-relational mappings.
    //select * from employee where

}
