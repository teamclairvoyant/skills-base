package com.prd.skillbase.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
public class UserLoginRegistrationController {

    @GetMapping("/")
    public String sayHelloAdmin(){
        return "Hello Admin! Welcome to SkillsBase Tool...!!!";
    }

    @GetMapping("/prevent")
    public Principal prevent(Principal principal)
    {
        return principal;
    }

}
