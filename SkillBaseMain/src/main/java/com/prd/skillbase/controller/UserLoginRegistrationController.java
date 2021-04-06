package com.prd.skillbase.controller;


import com.prd.skillbase.enums.Status;
import com.prd.skillbase.model.User;
import com.prd.skillbase.repository.UserRepository;

import com.prd.skillbase.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import java.security.Principal;



@RestController
@RequestMapping("/employees")
public class UserLoginRegistrationController {

    private UserService userService;

    private static final Logger LOGGER = LogManager.getLogger(UserLoginRegistrationController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    public UserLoginRegistrationController (UserService userService){
       this.userService = userService;
    }

    @CrossOrigin()
    @PostMapping("")
    public Status registerUser(@RequestBody User newUser) {

        User userExists = userService.findByEmail(newUser.getEmail());
        LOGGER.info("New User: " + newUser.toString());
        LOGGER.info(userExists);

        if (userExists != null){
            LOGGER.info("User Already Exists!");
            return Status.USER_ALREADY_EXISTS;
        }

        userService.saveUser(newUser);
        return Status.SUCCESS;
    }

    //get all employees
    @GetMapping("")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{employeeId}")
    public User findById(@PathVariable String employeeId){
        return userService.findById(employeeId);

    }

    @GetMapping("/prevent")
    public Principal prevent(Principal principal)
    {
        System.out.println("Prevent");
        return principal;

    }


}
