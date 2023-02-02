package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.model.User;
import com.clairvoyant.services.skillmatrix.model.UserHistory;
import com.clairvoyant.services.skillmatrix.repository.UserHistoryRepository;
import com.clairvoyant.services.skillmatrix.service.UserHistoryServiceDefault;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
