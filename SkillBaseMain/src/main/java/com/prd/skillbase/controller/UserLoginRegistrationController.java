package com.prd.skillbase.controller;


import com.prd.skillbase.enums.Status;
import com.prd.skillbase.model.User;
import com.prd.skillbase.repository.UserRepository;

import com.prd.skillbase.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import java.security.Principal;



@RestController
@RequestMapping("/employees")
public class UserLoginRegistrationController {

    private UserService userService;

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
        System.out.println("New User: " + newUser.toString());
        System.out.println(userExists);

        if (userExists != null){
            System.out.println("User Already Exists!");
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
