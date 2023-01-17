package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.dto.UserRoleDto;
import com.clairvoyant.clarise.model.UserRoleMapping;

import java.util.List;

public interface UserRoleService {

    List<UserRoleMapping> addOrUpdateUserRole(UserRoleDto userRoleDto);

    List<UserRoleMapping> findAllUserRoleMapping();

    List<String> findRoleIdByUserId(String userId);
}
