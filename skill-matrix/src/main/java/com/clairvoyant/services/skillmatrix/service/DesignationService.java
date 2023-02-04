package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.model.Designation;
import java.util.List;
import java.util.Optional;


public interface DesignationService {

    Designation save(Designation designation);

    Designation findById(String id, Optional<Boolean> isActive);

    void delete(Designation designation);

    List<Designation> findAll(Optional<Boolean> isActive);
}
