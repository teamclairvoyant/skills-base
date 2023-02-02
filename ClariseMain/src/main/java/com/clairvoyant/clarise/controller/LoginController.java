package com.clairvoyant.clarise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clairvoyant.clarise.dto.LoginDto;
import com.clairvoyant.clarise.dto.LoginResponseDto;
import com.clairvoyant.clarise.model.User;
import com.clairvoyant.clarise.repository.UserRepository;
import com.clairvoyant.clarise.service.UserHistoryServiceDefault;
import com.clairvoyant.clarise.service.impl.UserDetailsServiceDefault;
import com.clairvoyant.clarise.util.JWTUtility;

@RestController
@RequestMapping("/skillbase")
public class LoginController {
	
	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceDefault userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserHistoryServiceDefault userHistoryServie;

  
    @GetMapping("/test")
    public String sayHelloAdmin(){
        return "Hello Admin! Welcome to SkillsBase Tool...!!!";
    }
    
    /**
	 * login api
	 * @param loginRequest
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/login")
	public LoginResponseDto authenticateUser(@RequestBody LoginDto loginRequest) throws Exception {
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginRequest.getEmailAddress(), loginRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getEmailAddress());
		User userObj = userRepository.findByEmailAddress(loginRequest.getEmailAddress());
		final String token = jwtUtility.generateToken(userDetails);
		if(userObj!=null && token!=null) {
			userHistoryServie.saveUserHistory(userObj);
		}
		return new LoginResponseDto(userObj.getName(),
				userObj.getEmailAddress(),
				userObj.getReportingManager(),
				userObj.getGrade(),
				token);
	}

}