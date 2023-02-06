package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.dto.UserRoleDto;
import com.clairvoyant.services.skillmatrix.model.UserRoleMapping;
import java.util.List;
import java.util.Optional;

public interface UserRoleService {

    List<UserRoleMapping> addOrUpdateUserRole(UserRoleDto userRoleDto);

    List<UserRoleMapping> findAllUserRoleMapping();

    Optional<List<UserRoleMapping>> findUserRoleMappingByUserId(String userId);

    Optional<List<UserRoleMapping>> findUserRoleMappingByUserIdAndIsActive(String id, boolean b);
}
