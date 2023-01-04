package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.model.SkillsRating;

import java.util.List;

public interface SkillsRatingService {

    public SkillsRating save(SkillsRating skillsRating);

    public SkillsRating findById(String id);

    public void delete(SkillsRating rating);

    public List<SkillsRating> findAll();
}
