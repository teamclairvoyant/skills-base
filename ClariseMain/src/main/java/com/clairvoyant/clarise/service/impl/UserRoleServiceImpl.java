package com.clairvoyant.clarise.service.impl;

import com.clairvoyant.clarise.dto.UserRoleDto;
import com.clairvoyant.clarise.model.*;
import com.clairvoyant.clarise.repository.UserRoleRepository;
import com.clairvoyant.clarise.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRoleMapping> addOrUpdateUserRole(UserRoleDto userRoleDto) {
        Optional<UserRoleMapping> userRoleMapping = userRoleRepository.findByUserId(userRoleDto.getUserId());

        if (userRoleMapping.isEmpty())
            newUserRoleMapping(userRoleDto.getUserId(), userRoleDto.getRoleIds());

        return userRoleRepository.findAll();
    }

    @Override
    public List<UserRoleMapping> findAllUserRoleMapping() {
        return userRoleRepository.findAll();
    }

    @Override
    public List<String> findRoleIdByUserId(String userId) {
        return userRoleRepository.getRoleIdByUserId(userId);
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

//    @Override
//    public List<User> allUsers(String roleId) {
//        List<UserRoleMapping> userRoleMappings = userRoleRepository.findByRoleIdAndIsActive(roleId,true);
//
//        return userRoleMappings.stream().map(userRoleMapping -> userRoleMapping.getUser()).collect(Collectors.toList());
//
//    }
}
