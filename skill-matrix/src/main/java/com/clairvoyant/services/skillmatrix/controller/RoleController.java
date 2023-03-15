package com.clairvoyant.services.skillmatrix.controller;

import com.clairvoyant.services.skillmatrix.dto.UserResponseDto;
import com.clairvoyant.services.skillmatrix.model.Category;
import com.clairvoyant.services.skillmatrix.model.Role;
import com.clairvoyant.services.skillmatrix.service.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    public Role saveRole(@RequestBody Role role) {
        return roleService.addOrUpdateRole(role);
    }

    @GetMapping
    public List<Role> findAll() {
        return roleService.findAll();
    }
}
