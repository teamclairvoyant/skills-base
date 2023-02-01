package com.clairvoyant.services.skillmatrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clairvoyant.services.skillmatrix.model.QualificationStatus;

public interface QualificationStatusRepository extends JpaRepository<QualificationStatus, String> {

}
