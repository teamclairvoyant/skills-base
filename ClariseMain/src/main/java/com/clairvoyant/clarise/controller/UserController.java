package com.clairvoyant.clarise.controller;

import com.clairvoyant.clarise.dto.UserDto;
import com.clairvoyant.clarise.dto.UserResponseDto;
import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.repository.UserRepository;
import com.clairvoyant.clarise.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(SkillController.class);
    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }


    @GetMapping
    public List<UserResponseDto> getAllUsers(@RequestParam(name = "isActive",defaultValue = "true") Optional<Boolean> isActive)
    {
        return userService.findAll(isActive);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUserById(@PathVariable String userId,@RequestParam(name = "isActive",defaultValue = "true") Optional<Boolean> isActive)
    {
        return userService.findById(userId,isActive);
    }

    @PostMapping
    public Status addOrUpdateUser(@RequestBody UserDto userDto) {

        LOGGER.info(userDto);
        Status status = userService.addOrUpdateUser(userDto);

        return status;
    }

    @DeleteMapping("/{userId}")
    public Status delete(@PathVariable String userId)
    {
        return userService.delete(userId);
    }
}


