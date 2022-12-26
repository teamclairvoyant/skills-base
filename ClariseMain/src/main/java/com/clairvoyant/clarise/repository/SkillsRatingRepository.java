package com.clairvoyant.clarise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clairvoyant.clarise.model.SkillsRating;

public interface SkillsRatingRepository extends JpaRepository<SkillsRating, String>{

	public Optional<SkillsRating> findByIdAndIsActive(String id,boolean isActive);
	
	public List<SkillsRating> findByIsActive(boolean isActive);
}
