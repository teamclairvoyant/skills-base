package com.clairvoyant.clarise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clairvoyant.clarise.model.CustomUserDetails;
import com.clairvoyant.clarise.model.User;
import com.clairvoyant.clarise.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if(user==null) {
			throw new UsernameNotFoundException("UserNotFound");
		}
		return new CustomUserDetails(user);
	}

}
