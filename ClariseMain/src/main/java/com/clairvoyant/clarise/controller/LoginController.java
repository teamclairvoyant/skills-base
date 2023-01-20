package com.clairvoyant.clarise.controller;

import com.clairvoyant.clarise.service.impl.CustomUserDetailsService;
import com.clairvoyant.clarise.util.JwtRequest;
import com.clairvoyant.clarise.util.JwtResponse;
import com.clairvoyant.clarise.util.JwtUtils;

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


@RestController
@RequestMapping("/employees")
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;
	
	 @Autowired
	 private CustomUserDetailsService userService;

	@GetMapping("/test")
	public String sayHelloAdmin() {
		return "Hello Admin! Welcome to SkillsBase Tool...!!!";
	}

	/**
	 * login api
	 * @param loginRequest
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/login")
	public JwtResponse authenticateUser(@RequestBody JwtRequest loginRequest) throws Exception {
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword()));
		}catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
		 SecurityContextHolder.getContext().setAuthentication(authentication);
		 final UserDetails userDetails= userService.loadUserByUsername(loginRequest.getUserName());
		 final String token = jwtUtils.generateToken(userDetails);
		 return new JwtResponse(loginRequest.getUserName(),token);
	}
}