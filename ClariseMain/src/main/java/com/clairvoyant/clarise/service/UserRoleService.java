package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.dto.UserRoleDto;
import com.clairvoyant.clarise.model.UserRoleMapping;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {

    List<UserRoleMapping> addOrUpdateUserRole(UserRoleDto userRoleDto);

    List<UserRoleMapping> findAllUserRoleMapping();

    Optional<List<UserRoleMapping>> findUserRoleMappingByUserId(String userId);
}
