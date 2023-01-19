package com.clairvoyant.clarise.service;


import com.clairvoyant.clarise.dto.UserDesignationDto;
import com.clairvoyant.clarise.model.User;

import java.util.List;

public interface UserDesignationService {

    void  addOrUpdateUserDesignation(UserDesignationDto userDesignationDto);
    List<User> allUsers(String designationId);
}
