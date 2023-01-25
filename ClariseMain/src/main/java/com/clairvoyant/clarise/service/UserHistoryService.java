package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.model.User;
import com.clairvoyant.clarise.model.UserHistory;

public interface UserHistoryService {
	
	UserHistory saveUserHistory(User userObj);

}
