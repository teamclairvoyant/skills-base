package com.clairvoyant.clarise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clairvoyant.clarise.configuration.SwaggerConfig;
import com.clairvoyant.clarise.model.SkillsRating;
import com.clairvoyant.clarise.service.SkillsRatingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/skillsrating")
@Api(tags = SwaggerConfig.SKILLS_RATING)
public class SkillsRatingController {

	@Autowired
	private SkillsRatingService service;

	@PostMapping
	@ApiOperation(value = "Create and update the skills rating", response = SkillsRating.class)
	public SkillsRating addRating(@RequestBody SkillsRating skillsRating) {
		return service.save(skillsRating);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Gets Skills rating by id", response = SkillsRating.class)
	public SkillsRating findById(@PathVariable String id) {
		return service.findById(id);
	}

	@GetMapping
	@ApiOperation(value = "Get all skills rating", response = List.class)
	public List<SkillsRating> findAll() {
		return service.findAll();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletes the skill rating by id", response = String.class)
	public String deleteRating(@PathVariable String id) {
		SkillsRating rating = new SkillsRating();
		rating.setId(id);
		service.delete(rating);
		return "Record Deleted";
	}

}
