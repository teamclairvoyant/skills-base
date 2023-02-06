package com.clairvoyant.services.skillmatrix.service;


import com.clairvoyant.services.skillmatrix.dto.UserDesignationDto;
import com.clairvoyant.services.skillmatrix.model.User;
import com.clairvoyant.services.skillmatrix.model.UserDesignationMapping;
import java.util.List;

public interface UserDesignationService {

    List<UserDesignationMapping> addOrUpdateUserDesignation(UserDesignationDto userDesignationDto);

    List<User> allUsers(String designationId);

    List<UserDesignationMapping> findAllUserDesignationMappings();

    List<UserDesignationMapping> findUserDesignationMappingByUserId(String userId);

    UserDesignationMapping findUserDesignationMappingByUserIdAndIsActive(String id, boolean b);
}

