package com.clairvoyant.services.skillmatrix.repository;

import com.clairvoyant.services.skillmatrix.model.QualificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationStatusRepository extends JpaRepository<QualificationStatus, String> {

}
