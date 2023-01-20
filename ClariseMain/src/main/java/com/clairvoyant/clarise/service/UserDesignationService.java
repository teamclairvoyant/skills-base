package com.clairvoyant.clarise.service;


import com.clairvoyant.clarise.dto.UserDesignationDto;
import com.clairvoyant.clarise.model.User;
import com.clairvoyant.clarise.model.UserDesignationMapping;

import java.util.List;
import java.util.Optional;

public interface UserDesignationService {

    void  addOrUpdateUserDesignation(UserDesignationDto userDesignationDto);
    List<User> allUsers(String designationId);

    List<UserDesignationMapping> findAllUserDesignationMappings();

    UserDesignationMapping findUserDesignationMappingByUserId(String userId);

}

