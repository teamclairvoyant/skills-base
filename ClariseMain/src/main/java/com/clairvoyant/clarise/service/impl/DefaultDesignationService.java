package com.clairvoyant.clarise.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.clairvoyant.clarise.exceptions.ResourceNotFoundException;
import com.clairvoyant.clarise.model.Designation;
import com.clairvoyant.clarise.repository.DesignationRepository;
import com.clairvoyant.clarise.service.DesignationService;

@Service
public class DefaultDesignationService implements DesignationService {

	@Autowired
	private DesignationRepository repository;

	@Override
	public Designation save(Designation designation) {
		if (StringUtils.hasText(designation.getId())) {
			Optional<Designation> result = repository.findById(designation.getId());
			if (result.isPresent()) {
				if (StringUtils.hasLength(designation.getName()))
					result.get().setName(designation.getName());

				if (StringUtils.hasLength(designation.getDescription()))
					result.get().setDescription(designation.getDescription());

				designation = result.get();
			}

		} else {
			designation.setCreatedBy("");
			designation.setActive(true);
		}
		return repository.save(designation);
	}

	@Override
	public Designation findById(String id, Optional<Boolean> isActive) {
		Optional<Designation> result;
		if (isActive.isPresent()) {
			result = repository.findByIdAndIsActive(id, isActive.get());
		}else {
			result = repository.findByIdAndIsActive(id, true);
		}
		if (result.isEmpty()) {
			throw new ResourceNotFoundException("Designation Not Found");
		}
		return result.get();

	}

	@Override
	public void delete(Designation designation) {
		Optional<Designation> result = repository.findById(designation.getId());
		if (result.isPresent()) {
			result.get().setActive(false);
			repository.save(result.get());
		} else {
			throw new ResourceNotFoundException("Designation not found");
		}
	}

	@Override
	public List<Designation> findAll(Optional<Boolean>  isActive) {
		Optional<List<Designation>> result;
		if (isActive.isPresent()) {
			result = repository.findByIsActive(isActive.get());
		}else {
			result = repository.findByIsActive(true);
		}
		return result.get();
	}

}
