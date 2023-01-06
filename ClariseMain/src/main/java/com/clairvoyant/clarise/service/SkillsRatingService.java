package com.clairvoyant.clarise.service;

import java.util.List;

import com.clairvoyant.clarise.model.SkillsRating;

public interface SkillsRatingService {

	public SkillsRating save(SkillsRating skillsRating);

	public SkillsRating findById(String id);

	public void delete(SkillsRating rating);

	public List<SkillsRating> findAll();
}
