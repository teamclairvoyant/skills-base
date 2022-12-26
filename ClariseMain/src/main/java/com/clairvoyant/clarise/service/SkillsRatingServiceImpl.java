package com.clairvoyant.clarise.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.clairvoyant.clarise.exceptions.SkillRatingNotFoundException;
import com.clairvoyant.clarise.model.SkillsRating;
import com.clairvoyant.clarise.repository.SkillsRatingRepository;

@Service
public class SkillsRatingServiceImpl implements SkillsRatingService {

	@Autowired
	private SkillsRatingRepository ratingRepository;

	@Override
	public SkillsRating save(SkillsRating skillsRating) {
		if (StringUtils.hasText(skillsRating.getId())) {
			Optional<SkillsRating> result = ratingRepository.findById(skillsRating.getId());
			if (result.isPresent()) {
				if (StringUtils.hasLength(skillsRating.getName()))
					result.get().setName(skillsRating.getName());

				if (StringUtils.hasLength(skillsRating.getDescription()))
					result.get().setDescription(skillsRating.getDescription());

				result.get().setUpdatedBy("");
				result.get().setUpdatedOn(Instant.now());
				skillsRating = result.get();
			}

		} else {
			skillsRating.setCreatedOn(Instant.now());
			skillsRating.setCreatedBy("");
			skillsRating.setActive(true);
		}
		return ratingRepository.save(skillsRating);
	}

	@Override
	public SkillsRating findById(String id) {
		Optional<SkillsRating> result = ratingRepository.findByIdAndIsActive(id, true);
		System.out.println("the result of findby id is:" + result);
		if (result.isEmpty()) {
			throw new SkillRatingNotFoundException("Skill Rating Not Found");
		}
		return result.get();
	}

	@Override
	public void delete(SkillsRating rating) {
		SkillsRating result = findById(rating.getId());

		result.setActive(false);
		save(result);

	}

	@Override
	public List<SkillsRating> findAll() {
		List<SkillsRating> result = ratingRepository.findByIsActive(true);
		return result;
	}

}
