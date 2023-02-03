package com.clairvoyant.services.skillmatrix.controller;

import com.clairvoyant.services.skillmatrix.configuration.SwaggerConfig;
import com.clairvoyant.services.skillmatrix.model.SkillsRating;
import com.clairvoyant.services.skillmatrix.service.SkillsRatingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public SkillsRating findById(@PathVariable String id, @RequestParam(name = "isActive", defaultValue = "true") Optional<Boolean> isActive) {
        return service.findById(id, isActive);
    }

    @GetMapping
    @ApiOperation(value = "Get all skills rating", response = List.class)
    public List<SkillsRating> findAll(@RequestParam(name = "isActive", defaultValue = "true") Optional<Boolean> isActive) {
        return service.findAll(isActive);
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
