package com.clairvoyant.clarise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clairvoyant.clarise.model.SkillsRating;
import com.clairvoyant.clarise.service.SkillsRatingService;

@RestController
@RequestMapping("/v1/skillsrating")
public class SkillsRatingController {

	@Autowired
	private SkillsRatingService service;

	@PostMapping()
	public SkillsRating addRating(@RequestBody SkillsRating skillsRating) {
		return service.save(skillsRating);
	}

	@GetMapping("/{id}")
	public SkillsRating findById(@PathVariable String id) {
		return service.findById(id);
	}

	@GetMapping()
	public List<SkillsRating> findAll() {
		return service.findAll();
	}

	@DeleteMapping("/{id}")
	public String deleteRating(@PathVariable String id) {
		SkillsRating rating = new SkillsRating();
		rating.setId(id);
		service.delete(rating);
		return "Record Deleted";
	}

}
