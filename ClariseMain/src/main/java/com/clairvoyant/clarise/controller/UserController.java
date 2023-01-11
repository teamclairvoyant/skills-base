package com.clairvoyant.clarise.controller;

import com.clairvoyant.clarise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.clairvoyant.clarise.model.User;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public String saveUser(@RequestBody User user) {
        User user1 = userService.save(user);
        return  "save successful";
    }
}
