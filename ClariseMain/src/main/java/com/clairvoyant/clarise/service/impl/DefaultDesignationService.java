package com.clairvoyant.clarise.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.clairvoyant.clarise.exceptions.ResourceNotFoundException;
import com.clairvoyant.clarise.entities.Designation;
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

				result.get().setUpdatedBy("");
				result.get().setUpdatedOn(Instant.now());
				designation = result.get();
			}

		} else {
			designation.setCreatedOn(Instant.now());
			designation.setCreatedBy(designation.getCreatedBy());
			designation.setActive(true);
		}
		return repository.save(designation);
	}

	@Override
	public Designation findById(String id) {
		Optional<Designation> result = repository.findById(id);
		if (result.isEmpty()) {
			throw new ResourceNotFoundException("Designation Not Found");
		}
		return result.get();
	}

	@Override
	public void delete(Designation designation) {
		Designation result = findById(designation.getId());
		result.setActive(false);
		repository.save(result);
	}

	@Override
	public List<Designation> findAll() {
		List<Designation> result = repository.findAll();
		return result;
	}

}
