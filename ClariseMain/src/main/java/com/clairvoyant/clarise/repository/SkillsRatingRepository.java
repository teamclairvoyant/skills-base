package com.clairvoyant.clarise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clairvoyant.clarise.model.SkillsRating;

import java.util.List;
import java.util.Optional;

public interface SkillsRatingRepository extends JpaRepository<SkillsRating, String>{

    List<SkillsRating> findByIsActive(boolean b);

    Optional<SkillsRating> findByIdAndIsActive(String id, boolean b);
}
