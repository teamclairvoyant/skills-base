package com.clairvoyant.services.skillmatrix.service;

import java.util.List;
import java.util.Optional;

import com.clairvoyant.services.skillmatrix.model.Designation;


public interface DesignationService {

	public Designation save(Designation designation);

	public Designation findById(String id, Optional<Boolean> isActive);

	public void delete(Designation designation);

	public List<Designation> findAll(Optional<Boolean>  isActive);
}
