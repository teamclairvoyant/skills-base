package com.clairvoyant.services.skillmatrix.repository;

import com.clairvoyant.services.skillmatrix.model.UserDesignationMapping;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDesignationRepository extends JpaRepository<UserDesignationMapping, String> {

    UserDesignationMapping findByUserId(String userId);

    List<UserDesignationMapping> findByDesignationIdAndIsActive(String designationId, boolean isActive);

}
