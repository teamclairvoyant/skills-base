package com.clairvoyant.clarise.service;

import java.util.List;

import com.clairvoyant.clarise.model.Designation;


public interface DesignationService {

	public Designation save(Designation designation);

	public Designation findById(String id);

	public void delete(Designation designation);

	public List<Designation> findAll();
}
