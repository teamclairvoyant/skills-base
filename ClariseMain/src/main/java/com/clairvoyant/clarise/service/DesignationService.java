package com.clairvoyant.clarise.service;

import java.util.List;
import java.util.Optional;

import com.clairvoyant.clarise.model.Designation;


public interface DesignationService {

	public Designation save(Designation designation);

	public Designation findById(String id, Optional<Boolean> isActive);

	public void delete(Designation designation);

	public List<Designation> findAll(Optional<Boolean>  isActive);
}
