package com.clairvoyant.clarise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clairvoyant.clarise.model.User;
import com.clairvoyant.clarise.repository.UserRepository;
import com.clairvoyant.clarise.service.UserDetailsImpl;

@Service
public class UserDetailsServiceDefault implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// logic to get the user from the database
		User user = userRepository.findByEmailAddress(userName);
		if (user == null) {
			throw new UsernameNotFoundException("UserNotFound");
		}
		return new UserDetailsImpl(user);
	}

}
