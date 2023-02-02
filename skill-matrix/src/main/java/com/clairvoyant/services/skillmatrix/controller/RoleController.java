package com.clairvoyant.services.skillmatrix.controller;

import com.clairvoyant.services.skillmatrix.model.Role;
import com.clairvoyant.services.skillmatrix.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/saveRole")
    public Role saveRole(@RequestBody Role role) {
        return roleService.addOrUpdateRole(role);
    }

}
