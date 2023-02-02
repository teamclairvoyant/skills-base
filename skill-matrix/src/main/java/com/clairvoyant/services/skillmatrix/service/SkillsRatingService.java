package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.enums.Status;
import com.clairvoyant.services.skillmatrix.model.SkillsRating;
import java.util.List;
import java.util.Optional;

public interface SkillsRatingService {

    SkillsRating save(SkillsRating skillsRating);

    SkillsRating findById(String id, Optional<Boolean> isActive);

    Status delete(SkillsRating rating);

    List<SkillsRating> findAll(Optional<Boolean> isActive);
}
