package com.clairvoyant.clarise.controller;


import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.model.AccessToken;
import com.clairvoyant.clarise.model.User;
import com.clairvoyant.clarise.repository.UserRepository;

import com.clairvoyant.clarise.service.MailService;
import com.clairvoyant.clarise.service.UserService;

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
import java.security.Principal;



@RestController
@RequestMapping("/employees")
public class UserLoginRegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    private static final Logger LOGGER = LogManager.getLogger(UserLoginRegistrationController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    public UserLoginRegistrationController (UserService userService){
       this.userService = userService;
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
    @GetMapping("/test")
    public String sayHelloAdmin(){
        return "Hello Admin! Welcome to SkillsBase Tool...!!!";
    }

    @GetMapping("/prevent")
    public AccessToken prevent(@AuthenticationPrincipal OidcUser principal, HttpServletResponse httpResponse) throws IOException {

        // Get ID Token Object
        OidcIdToken idToken = principal.getIdToken();

        // Get ID Token Value
        String tokenValue = idToken.getTokenValue();
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(idToken.getTokenValue());
        // TODO: Change with redirect uri passed from ui
        httpResponse.sendRedirect("http://localhost:3000/oauth/redirect?access_token=" + idToken.getTokenValue());
        return accessToken;
    }



    //endpoint to send invitation link
    @PostMapping("/invitation")
    public Status sendEmail(@RequestBody User user) throws MessagingException {

        mailService.sendEmail(user);
        return Status.MESSAGE_SENT;
    }



}
