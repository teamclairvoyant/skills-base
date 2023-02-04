package com.clairvoyant.services.skillmatrix.repository;

import com.clairvoyant.services.skillmatrix.model.SkillsRating;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsRatingRepository extends JpaRepository<SkillsRating, String> {

    List<SkillsRating> findByIsActive(boolean b);

    Optional<SkillsRating> findByIdAndIsActive(String id, boolean b);
}
