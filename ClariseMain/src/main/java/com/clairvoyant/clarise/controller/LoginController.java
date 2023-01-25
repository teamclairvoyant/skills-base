package com.clairvoyant.clarise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clairvoyant.clarise.dto.JwtRequest;
import com.clairvoyant.clarise.dto.JwtResponse;
import com.clairvoyant.clarise.model.User;
import com.clairvoyant.clarise.repository.UserRepository;
import com.clairvoyant.clarise.service.UserHistoryService;
import com.clairvoyant.clarise.service.impl.CustomUserDetailsService;
import com.clairvoyant.clarise.util.JWTUtility;

@RestController
@RequestMapping("/skillbase")
public class LoginController {
	
	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserHistoryService userHistoryServie;

    @GetMapping("/test")
    public String sayHelloAdmin(){
        return "Hello Admin! Welcome to SkillsBase Tool...!!!";
    }

    /**
	 * login api
	 * 
	 * @param loginRequest
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/login")
	public JwtResponse authenticateUser(@RequestBody JwtRequest loginRequest) throws Exception {
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginRequest.getEmailAdress(), loginRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getEmailAdress());
		User userObj = userRepository.findByEmailAddress(loginRequest.getEmailAdress());
		final String token = jwtUtility.generateToken(userDetails);
		if(userObj!=null && token!=null) {
			userHistoryServie.saveUserHistory(userObj);
		}
		return new JwtResponse(userObj.getName(),userObj.getEmailAddress(),userObj.getReportingManager(),userObj.getGrade(),token);
	}


}