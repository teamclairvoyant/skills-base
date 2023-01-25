package com.clairvoyant.clarise.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clairvoyant.clarise.model.User;
import com.clairvoyant.clarise.model.UserHistory;
import com.clairvoyant.clarise.repository.UserHistoryRepository;
import com.clairvoyant.clarise.service.UserHistoryService;

@Service
public class UserHistoryServiceImpl implements  UserHistoryService {
	
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
