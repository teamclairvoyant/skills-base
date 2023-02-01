package com.clairvoyant.services.skillmatrix.service;

import java.util.List;
import java.util.Optional;

import com.clairvoyant.services.skillmatrix.enums.Status;
import com.clairvoyant.services.skillmatrix.model.SkillsRating;

public interface SkillsRatingService {

	public SkillsRating save(SkillsRating skillsRating);

	public SkillsRating findById(String id, Optional<Boolean> isActive);

	public Status delete(SkillsRating rating);

	public List<SkillsRating> findAll(Optional<Boolean> isActive);
}
