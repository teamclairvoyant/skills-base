package com.clairvoyant.clarise.controller;


import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.model.User;
import com.clairvoyant.clarise.repository.UserRepository;
import com.clairvoyant.clarise.service.MailService;
import com.clairvoyant.clarise.service.UserService;
import com.clairvoyant.clarise.service.ValidateDomainService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.io.IOException;


@RestController
@RequestMapping("/employees")
public class UserLoginRegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private ValidateDomainService validateDomainService;

    private static final Logger LOGGER = LogManager.getLogger(UserLoginRegistrationController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    public UserLoginRegistrationController (UserService userService){
       this.userService = userService;
    }

    //get all employees
    @GetMapping("/admin/employess")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/user/{employeeId}")
    public User findById(@PathVariable String employeeId){
        return userService.findById(employeeId);

    }
    @GetMapping("/user/test")
    public String sayHelloAdmin(){
        return "Hello Admin! Welcome to SkillsBase Tool...!!!";
    }

    @GetMapping("/login")
    public void prevent(@AuthenticationPrincipal OidcUser principal, HttpServletResponse httpResponse) throws IOException {

        // Get ID Token Object
        OidcIdToken idToken = principal.getIdToken();

        // call service
        validateDomainService.checkDomain(idToken,httpResponse);

        // TODO: Change with redirect uri passed from ui
    }

    //update Employee/user rest controller

    @PutMapping("admin/update/{empId}")
    public Status updateEmployee(@PathVariable String empId, @RequestBody User userDetails){

        userService.updateUser(empId,userDetails);

        return Status.SUCCESS;
    }
    /*@PutMapping("/update")
    public User updateUser(@RequestBody User user){
        userService.saveUser(user);
        return user;
    }*/


    //endpoint to send invitation link
    @PostMapping("/invitation")
    public Status sendEmail(@RequestBody User user) throws MessagingException {

        mailService.sendEmail(user);
        return Status.MESSAGE_SENT;
    }



}
