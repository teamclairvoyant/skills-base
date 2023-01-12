package com.clairvoyant.clarise.controller;


import com.clairvoyant.clarise.dto.UserDesignationDto;
import com.clairvoyant.clarise.model.User;
import com.clairvoyant.clarise.model.UserDesignationMapping;
import com.clairvoyant.clarise.service.UserDesignationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userdesignation")
public class UserDesignationController {

    private static final Logger LOGGER = LogManager.getLogger(SkillCategoryController.class);

    private UserDesignationService userDesignationService;


    @Autowired
    public UserDesignationController(UserDesignationService userDesignationService) {
        this.userDesignationService = userDesignationService;
    }

    @PutMapping
    public List<UserDesignationMapping> addOrUpdateUserDesignation(@RequestBody UserDesignationDto userDesignationDto) {

        LOGGER.info(userDesignationDto);

        return userDesignationService.addOrUpdateUserDesignation(userDesignationDto);
    }

    //get skills of a category
    @GetMapping("users/{designationId}")
    public List<User> getUsers(@PathVariable String designationId) {

        return userDesignationService.allUsers(designationId);

    }




}
