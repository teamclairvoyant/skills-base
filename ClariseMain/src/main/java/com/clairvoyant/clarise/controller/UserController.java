package com.clairvoyant.clarise.controller;

import com.clairvoyant.clarise.dto.UserDesignationDto;
import com.clairvoyant.clarise.dto.UserDto;
import com.clairvoyant.clarise.dto.UserResponseDto;
import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.repository.UserRepository;
import com.clairvoyant.clarise.service.UserDesignationService;
import com.clairvoyant.clarise.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.clairvoyant.clarise.model.User;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(SkillController.class);
    private UserService userService;


    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;

    }


    @GetMapping
    public List<UserResponseDto> getAllUsers()
    {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUserById(@PathVariable String userId)
    {
        return userService.findById(userId);
    }

    @PostMapping
    public User addOrUpdateUser(@RequestBody UserDto userDto) {

        LOGGER.info(userDto);
        User user = userService.addOrUpdateUser(userDto);

        return user;
    }

    @DeleteMapping("/{userId}")
    public Status delete(@PathVariable String userId)
    {
        userService.delete(userId);
        return Status.SUCCESS;
    }
}

