package com.clairvoyant.services.skillmatrix.controller;

import com.clairvoyant.services.skillmatrix.dto.UserDto;
import com.clairvoyant.services.skillmatrix.dto.UserResponseDto;
import com.clairvoyant.services.skillmatrix.enums.Status;
import com.clairvoyant.services.skillmatrix.service.UserService;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@RequestMapping("user")
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(SkillController.class);
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserResponseDto> getAllUsers(@RequestParam(name = "isActive",defaultValue = "true") Optional<Boolean> isActive) {
        return userService.findAll(isActive);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUserById(@PathVariable String userId,@RequestParam(name = "isActive",defaultValue = "true") Optional<Boolean> isActive) {
        return userService.findById(userId,isActive);
    }

    @PostMapping
    public Status addOrUpdateUser(@RequestBody UserDto userDto) {

        LOGGER.info(userDto);
        Status status = userService.addOrUpdateUser(userDto);

        return status;
    }

    @DeleteMapping("/{userId}")
    public Status delete(@PathVariable String userId) {
        return userService.delete(userId);
    }
}


