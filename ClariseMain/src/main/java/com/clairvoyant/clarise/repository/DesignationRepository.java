package com.clairvoyant.clarise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clairvoyant.clarise.model.Designation;

public interface DesignationRepository extends JpaRepository<Designation, String> {
	
	public Optional<Designation> findByIdAndIsActive(String id, boolean isActive);

	public List<Designation> findByIsActive(boolean isActive);
}
