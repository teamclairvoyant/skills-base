//package com.clairvoyant.clarise.controller;
//
//import com.clairvoyant.clarise.service.ValidateDomainService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.oidc.OidcIdToken;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/employees/login")
//public class LoginController {
//
//    @Autowired
//    private ValidateDomainService validateDomainService;
//
//    @GetMapping
//    public void prevent(@AuthenticationPrincipal OidcUser principal, HttpServletResponse httpResponse) throws IOException {
//
//        // Get ID Token Object
//        OidcIdToken idToken = principal.getIdToken();
//
//        // call service
//        validateDomainService.checkDomain(idToken,httpResponse);
//    }
//    @GetMapping("/test")
//    public String sayHelloAdmin(){
//        return "Hello Admin! Welcome to SkillsBase Tool...!!!";
//    }
//
//}