package com.clairvoyant.clarise.controller;

import com.clairvoyant.clarise.model.Role;
import com.clairvoyant.clarise.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/saveRole")
    public Role saveRole(@RequestBody Role role){
        return roleService.addOrUpdateRole(role);
    }

}
