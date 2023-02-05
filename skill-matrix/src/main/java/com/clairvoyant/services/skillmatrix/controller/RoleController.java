package com.clairvoyant.services.skillmatrix.controller;

import com.clairvoyant.services.skillmatrix.model.Role;
import com.clairvoyant.services.skillmatrix.model.Skill;
import com.clairvoyant.services.skillmatrix.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/saveRole")
    public Role saveRole(@RequestBody Role role) {
        return roleService.addOrUpdateRole(role);
    }

    @GetMapping("/roles")
    public List<Role> findAll() {

        return roleService.findAll();
    }

    @PutMapping("/role/{roleId}")
    public Role addOrUpdateSkill(@RequestBody Role role) {
        return roleService.addOrUpdateRole(role);
    }

}
