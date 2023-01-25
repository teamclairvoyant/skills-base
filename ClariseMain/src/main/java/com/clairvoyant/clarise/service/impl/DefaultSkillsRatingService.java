package com.clairvoyant.clarise.service.impl;

import java.util.List;
import java.util.Optional;

import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.model.Designation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.clairvoyant.clarise.exceptions.ResourceNotFoundException;
import com.clairvoyant.clarise.model.SkillsRating;
import com.clairvoyant.clarise.repository.SkillsRatingRepository;
import com.clairvoyant.clarise.service.SkillsRatingService;

@Service
public class DefaultSkillsRatingService implements SkillsRatingService {

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
				skillsRating = result.get();
			}

		} else {
			skillsRating.setActive(true);
		}
		return ratingRepository.save(skillsRating); 
	}

	@Override
	public SkillsRating findById(String id, Optional<Boolean> isActive) {
		Optional<SkillsRating> result;
		if (isActive.isPresent()) {
			result = ratingRepository.findByIdAndIsActive(id, isActive.get());
		}else {
			result = ratingRepository.findByIdAndIsActive(id, true);
		}
		if (result.isEmpty()) {
			throw new ResourceNotFoundException("SkillsRating Not Found");
		}
		return result.get();
	}

	@Override
	public Status delete(SkillsRating rating) {
		Optional<SkillsRating> result = ratingRepository.findById(rating.getId());
		if (result.isPresent()){
			try {
				result.get().setActive(false);
				ratingRepository.save(result.get());
				return Status.SUCCESS;
			}catch (Exception e){
				e.printStackTrace();
			}
		}else
		{
			throw new ResourceNotFoundException("SkillRating not found");
		}
		return Status.FAILURE;
	}

	@Override
	public List<SkillsRating> findAll(Optional<Boolean> isActive) {
		List<SkillsRating> result;
		if (isActive.isPresent()) {
			result = ratingRepository.findByIsActive(isActive.get());
		}else {
			result = ratingRepository.findByIsActive(true);
		}
		return result;
	}

}
