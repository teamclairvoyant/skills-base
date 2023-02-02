package com.clairvoyant.services.skillmatrix.service;


import com.clairvoyant.services.skillmatrix.dto.UserDesignationDto;
import com.clairvoyant.services.skillmatrix.model.User;
import com.clairvoyant.services.skillmatrix.model.UserDesignationMapping;
import java.util.List;

public interface UserDesignationService {

    void addOrUpdateUserDesignation(UserDesignationDto userDesignationDto);

    List<User> allUsers(String designationId);

    List<UserDesignationMapping> findAllUserDesignationMappings();

    UserDesignationMapping findUserDesignationMappingByUserId(String userId);

}

