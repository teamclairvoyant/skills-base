package com.prd.skillbase.controller;


import com.prd.skillbase.enums.Status;
import com.prd.skillbase.model.User;
import com.prd.skillbase.repository.UserRepository;

import com.prd.skillbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/")
public class UserLoginRegistrationController {

    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public UserLoginRegistrationController (UserService userService){
       this.userService = userService ;
    }

    @CrossOrigin()
    @PostMapping(value = "/signup")
    public Status registerUser(@RequestBody User newUser) {

        User userExists = userService.findByEmail(newUser.getEmail());
        System.out.println("New User: " + newUser.toString());
        System.out.println(userExists);

        if (userExists != null){
            System.out.println("User Already Exists!");
            return Status.USER_ALREADY_EXISTS;
        }

        userService.saveUser(newUser);
        return Status.SUCCESS;
    }
    @GetMapping("/prevent")
    public Principal prevent(Principal principal)
    {
        return principal;
    }

}
