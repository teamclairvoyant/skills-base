package com.clairvoyant.clarise.controller;

import com.clairvoyant.clarise.dto.UserDto;
import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.repository.UserRepository;
import com.clairvoyant.clarise.service.UserService;
import com.clairvoyant.clarise.util.PasswordUtil;
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
    private UserRepository userRepository;



    @Autowired
    public UserController(UserService userService,PasswordUtil encoder) {
        this.userService = userService;
    }


    @GetMapping
    public List<UserDto> getAllUsers()
    {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable String userId)
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

    // For checking password
//    @GetMapping("/checkpass")
//    public Boolean check(@RequestBody User user)
//    {
//        User user1 = userRepository.findById(user.getId()).get();
//        String user1pass=user1.getPassword();
//        String userpass=user.getPassword();
//
//        if(PasswordUtil.matches(userpass,user1pass))
//        {
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
}


