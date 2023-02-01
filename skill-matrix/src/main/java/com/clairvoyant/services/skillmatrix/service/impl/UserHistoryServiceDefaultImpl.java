package com.clairvoyant.services.skillmatrix.service.impl;

import java.util.Date;

import com.clairvoyant.services.skillmatrix.model.UserHistory;
import com.clairvoyant.services.skillmatrix.service.UserHistoryServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clairvoyant.services.skillmatrix.model.User;
import com.clairvoyant.services.skillmatrix.repository.UserHistoryRepository;

@Service
public class UserHistoryServiceDefaultImpl implements UserHistoryServiceDefault {
	
	@Autowired
	private UserHistoryRepository userHistoryRepository;
	
	@Override
	public UserHistory saveUserHistory(User userObj) {
		UserHistory userHistory = new UserHistory();
		userHistory.setUser(userObj);
		userHistory.setLoginTime(new Date());
		userHistoryRepository.save(userHistory);
		return userHistory;
	}

}
