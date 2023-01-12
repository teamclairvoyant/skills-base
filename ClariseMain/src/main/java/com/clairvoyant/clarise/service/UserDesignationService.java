package com.clairvoyant.clarise.service;


import com.clairvoyant.clarise.dto.UserDesignationDto;
import com.clairvoyant.clarise.model.User;
import com.clairvoyant.clarise.model.UserDesignationMapping;

import java.util.List;

public interface UserDesignationService {

    public List<UserDesignationMapping>  addOrUpdateUserDesignation(UserDesignationDto userDesignationDto);

    public List<User> allUsers(String designationId);
}
