package com.clairvoyant.clarise.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.clairvoyant.clarise.exceptions.DesignationNotFoundException;
import com.clairvoyant.clarise.model.Designation;
import com.clairvoyant.clarise.repository.DesignationRepository;

@Service
public class DesignationServiceImpl implements DesignationService {

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
			designation.setCreatedBy("");
			designation.setActive(true);
		}
		return repository.save(designation);
	}

	@Override
	public Designation findById(String id) {
		Optional<Designation> result = repository.findByIdAndIsActive(id, true);
		if (result.isEmpty()) {
			throw new DesignationNotFoundException("Designation Not Found");
		}
		return result.get();
	}

	@Override
	public void delete(Designation designation) {
		Designation result = findById(designation.getId());
		result.setActive(false);
		save(result);
	}

	@Override
	public List<Designation> findAll() {
		List<Designation> result = repository.findByIsActive(true);
		return result;
	}

}
