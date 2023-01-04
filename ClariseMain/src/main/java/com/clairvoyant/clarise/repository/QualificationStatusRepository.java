package com.clairvoyant.clarise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clairvoyant.clarise.entities.QualificationStatus;

public interface QualificationStatusRepository extends JpaRepository<QualificationStatus, String> {

}
