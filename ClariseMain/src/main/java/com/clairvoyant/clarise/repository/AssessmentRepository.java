package com.clairvoyant.clarise.repository;

import com.clairvoyant.clarise.model.Assessment;
import com.clairvoyant.clarise.model.AssessmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentRepository extends JpaRepository<Assessment,String> {
}
