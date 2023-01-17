package com.clairvoyant.clarise.service.impl;

import com.clairvoyant.clarise.model.Role;
import com.clairvoyant.clarise.repository.RoleRepository;
import com.clairvoyant.clarise.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role addOrUpdateRole(Role role) {
        return roleRepository.save(role);
    }
}
