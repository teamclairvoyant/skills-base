package com.clairvoyant.clarise.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.clairvoyant.clarise.exceptions.QualificationStatusNotFoundException;
import com.clairvoyant.clarise.model.QualificationStatus;
import com.clairvoyant.clarise.repository.QualificationStatusRepository;

@Service
public class QualificationStatusServiceImpl implements QualificationStatusService {

	@Autowired
	private QualificationStatusRepository repository;

	@Override
	public QualificationStatus save(QualificationStatus qualificationStatus) {
		if (StringUtils.hasText(qualificationStatus.getId())) {
			Optional<QualificationStatus> result = repository.findById(qualificationStatus.getId());
			if (result.isPresent()) {
				if (StringUtils.hasLength(qualificationStatus.getName()))
					result.get().setName(qualificationStatus.getName());

				if (StringUtils.hasLength(qualificationStatus.getDescription()))
					result.get().setDescription(qualificationStatus.getDescription());

				result.get().setUpdatedBy("");
				result.get().setUpdatedOn(Instant.now());
				qualificationStatus = result.get();
			}

		} else {
			qualificationStatus.setCreatedOn(Instant.now());
			qualificationStatus.setCreatedBy("");
			qualificationStatus.setActive(true);
		}
		
		return repository.save(qualificationStatus);
	}

	@Override
	public QualificationStatus findById(String id) {
		Optional<QualificationStatus> result = repository.findByIdAndIsActive(id, true);
		if (result.isEmpty()) {
			throw new QualificationStatusNotFoundException("Qualification Status Not Found");
		}

		return result.get();
	}

	@Override
	public void delete(QualificationStatus qualificationStatus) {
		QualificationStatus result = findById(qualificationStatus.getId());
		result.setActive(false);
		save(result);
	}

	@Override
	public List<QualificationStatus> findAll() {
		List<QualificationStatus> result = repository.findByIsActive(true);
		return result;
	}

}
