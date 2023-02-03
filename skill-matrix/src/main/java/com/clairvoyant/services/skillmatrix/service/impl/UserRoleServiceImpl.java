package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.dto.UserRoleDto;
import com.clairvoyant.services.skillmatrix.model.Role;
import com.clairvoyant.services.skillmatrix.model.User;
import com.clairvoyant.services.skillmatrix.model.UserRoleMapping;
import com.clairvoyant.services.skillmatrix.repository.UserRoleRepository;
import com.clairvoyant.services.skillmatrix.service.UserRoleService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRoleMapping> addOrUpdateUserRole(UserRoleDto userRoleDto) {
        Optional<List<UserRoleMapping>> userRoleMapping = userRoleRepository.findByUserId(userRoleDto.getUserId());

        if (userRoleMapping.get().isEmpty()) {
            newUserRoleMapping(userRoleDto.getUserId(), userRoleDto.getRoleIds());
        }

        return userRoleRepository.findAll();
    }

    @Override
    public List<UserRoleMapping> findAllUserRoleMapping() {
        return userRoleRepository.findAll();
    }

    @Override
    public Optional<List<UserRoleMapping>> findUserRoleMappingByUserId(String userId) {
        return userRoleRepository.findByUserId(userId);
    }

    private void newUserRoleMapping(String userId, List<String> roleIds) {

        for (String roleId : roleIds) {
            UserRoleMapping userRoleMapping = new UserRoleMapping();
            User user = new User();
            user.setId(userId);
            Role role = new Role();
            role.setId(roleId);
            userRoleMapping.setUser(user);
            userRoleMapping.setRoles(role);
            userRoleRepository.save(userRoleMapping);
        }
    }
}
