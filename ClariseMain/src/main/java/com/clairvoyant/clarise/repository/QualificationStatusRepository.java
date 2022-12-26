package com.clairvoyant.clarise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clairvoyant.clarise.model.QualificationStatus;

public interface QualificationStatusRepository extends JpaRepository<QualificationStatus, String> {
	
	public Optional<QualificationStatus> findByIdAndIsActive(String id, boolean isActive);

	public List<QualificationStatus> findByIsActive(boolean isActive);

}
