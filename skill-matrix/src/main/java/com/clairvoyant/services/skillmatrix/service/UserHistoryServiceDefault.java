package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.model.User;
import com.clairvoyant.services.skillmatrix.model.UserHistory;

public interface UserHistoryServiceDefault {

    UserHistory saveUserHistory(User userObj);

}
