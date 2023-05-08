package com.clairvoyant.services.skillmatrix.controller;

import com.clairvoyant.services.skillmatrix.dto.SkillMatrixResponse;
import com.clairvoyant.services.skillmatrix.service.SkillMatrixService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/skillmatrix")
public class SkillMatrixController {

    private static final Logger LOGGER = LogManager.getLogger(SkillMatrixController.class);

    @Autowired
    private SkillMatrixService skillMatrixService;

    //add or update skillcategory mapping

    @GetMapping("/{userId}")
    public SkillMatrixResponse getSkillMatrix(@PathVariable String userId, @RequestBody List<String> categoryIds) {

        return skillMatrixService.getSkills(userId, categoryIds);
    }
}
