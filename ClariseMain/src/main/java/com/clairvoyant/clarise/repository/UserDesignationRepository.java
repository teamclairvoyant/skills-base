package com.clairvoyant.clarise.repository;


import com.clairvoyant.clarise.model.SkillCategory;
import com.clairvoyant.clarise.model.UserDesignationMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDesignationRepository extends JpaRepository<UserDesignationMapping, String> {

    List<UserDesignationMapping> findByUserId(String userId);

    List<UserDesignationMapping> findByDesignationIdAndIsActive(String designationId, boolean isActive);


}
