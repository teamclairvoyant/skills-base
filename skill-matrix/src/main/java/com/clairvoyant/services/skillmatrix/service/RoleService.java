package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.model.Role;
import java.util.List;

public interface RoleService {

    Role addOrUpdateRole(Role role);
    List<Role> findAll();


}
