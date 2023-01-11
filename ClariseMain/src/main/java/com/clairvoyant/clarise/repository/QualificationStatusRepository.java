package com.clairvoyant.clarise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clairvoyant.clarise.model.QualificationStatus;

public interface QualificationStatusRepository extends JpaRepository<QualificationStatus, String> {

}
