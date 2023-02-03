package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.dto.UserRoleDto;
import com.clairvoyant.services.skillmatrix.model.Role;
import com.clairvoyant.services.skillmatrix.model.User;
import com.clairvoyant.services.skillmatrix.model.UserRoleMapping;
import com.clairvoyant.services.skillmatrix.repository.UserRoleRepository;
import com.clairvoyant.services.skillmatrix.service.UserRoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRoleMapping> addOrUpdateUserRole(UserRoleDto userRoleDto) {
        Optional<List<UserRoleMapping>> userRoleMapping = userRoleRepository.findByUserId(userRoleDto.getUserId());

        List<UserRoleMapping> userRoleMappings = new ArrayList<>();
        if (userRoleMapping.get().isEmpty()) {
            //Insert Roles for the first time
            newUserRoleMapping(userRoleDto.getUserId(), userRoleDto.getRoleIds(), userRoleMappings);
        } else {
            List<String> dbRoleIds = userRoleMapping.get().stream().map(userRole -> userRole.getRoles().getId()).collect(Collectors.toList());
            List<String> dbActiveRoleIds = userRoleMapping.get().stream().filter(UserRoleMapping::isActive)
                    .map(userRole -> userRole.getRoles().getId()).collect(Collectors.toList());

            //Insert new Role for the category -- create
            List<String> newRoleIds = new ArrayList<>(Sets.difference(Sets.newHashSet(userRoleDto.getRoleIds()), Sets.newHashSet(dbRoleIds)));
            newUserRoleMapping(userRoleDto.getUserId(), newRoleIds, userRoleMappings);

            //removing newly inserted skillIds from request so that newly inserted ids will not come while updating in the below
            userRoleDto.getRoleIds().removeIf(newRoleIds::contains);


            //update to inactive for existing mappings -- delete
            List<String> deletedRoleIds = new ArrayList<>(Sets.difference(Sets.newHashSet(dbActiveRoleIds), Sets.newHashSet(userRoleDto.getRoleIds())));
            for (String roleId : deletedRoleIds) {
                UserRoleMapping roleMapping =
                        userRoleMapping.get().stream().filter(urm -> roleId.equals(urm.getRoles().getId())).findFirst().get();
                roleMapping.setActive(false);
                userRoleMappings.add(roleMapping);
            }

            //update existing inactive mapping to true -- update
            List<String> updateRoleIds = new ArrayList<>(Sets.difference(Sets.newHashSet(userRoleDto.getRoleIds()), Sets.newHashSet(dbActiveRoleIds)));
            for (String roleId : updateRoleIds) {
                UserRoleMapping roleMapping =
                        userRoleMapping.get().stream().filter(urm -> roleId.equals(urm.getRoles().getId())).findFirst().get();
                roleMapping.setActive(true);
                userRoleMappings.add(roleMapping);
            }

        }
        if (Objects.nonNull(userRoleMappings) && userRoleMappings.size() > 0) {
            return userRoleRepository.saveAll(userRoleMappings);
        }
        return null;
    }

    @Override
    public List<UserRoleMapping> findAllUserRoleMapping() {
        return userRoleRepository.findByIsActive(true);
    }

    @Override
    public Optional<List<UserRoleMapping>> findUserRoleMappingByUserId(String userId) {
        return userRoleRepository.findByUserId(userId);
    }

    @Override
    public Optional<List<UserRoleMapping>> findUserRoleMappingByUserIdAndIsActive(String id, boolean b) {
        return userRoleRepository.findByUserIdAndIsActive(id,b);
    }

    private void newUserRoleMapping(String userId, List<String> roleIds, List<UserRoleMapping> userRoleMappings) {

        for (String roleId : roleIds) {
            UserRoleMapping userRoleMapping = new UserRoleMapping();
            User user = new User();
            user.setId(userId);
            Role role = new Role();
            role.setId(roleId);
            userRoleMapping.setUser(user);
            userRoleMapping.setRoles(role);
            userRoleMapping.setActive(true);
            userRoleMappings.add(userRoleMapping);
        }
    }
}
