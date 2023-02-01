package com.clairvoyant.services.skillmatrix.repository;

import com.clairvoyant.services.skillmatrix.model.UserDesignationMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDesignationRepository extends JpaRepository<UserDesignationMapping, String> {

    UserDesignationMapping findByUserId(String userId);

    List<UserDesignationMapping> findByDesignationIdAndIsActive(String designationId, boolean isActive);



}
