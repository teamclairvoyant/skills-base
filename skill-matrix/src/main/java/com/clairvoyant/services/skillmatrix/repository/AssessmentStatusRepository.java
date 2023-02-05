package com.clairvoyant.services.skillmatrix.repository;


import com.clairvoyant.services.skillmatrix.model.AssessmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentStatusRepository extends JpaRepository<AssessmentStatus,String> {
}
