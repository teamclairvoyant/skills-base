package com.clairvoyant.clarise.repository;

import com.clairvoyant.clarise.model.AssessmentStatus;
import com.clairvoyant.clarise.model.AssessmentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentTypeRepository extends JpaRepository<AssessmentType,String> {
}
