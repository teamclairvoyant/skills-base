package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.exceptions.ResourceNotFoundException;
import com.clairvoyant.services.skillmatrix.model.QualificationStatus;
import com.clairvoyant.services.skillmatrix.repository.QualificationStatusRepository;
import com.clairvoyant.services.skillmatrix.service.QualificationStatusService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DefaultQualificationStatusService implements QualificationStatusService {

    @Autowired
    private QualificationStatusRepository repository;

    @Override
    public QualificationStatus save(QualificationStatus qualificationStatus) {
        if (StringUtils.hasText(qualificationStatus.getId())) {
            Optional<QualificationStatus> result = repository.findById(qualificationStatus.getId());
            if (result.isPresent()) {
                if (StringUtils.hasLength(qualificationStatus.getName())) {
                    result.get().setName(qualificationStatus.getName());
                }

                if (StringUtils.hasLength(qualificationStatus.getDescription())) {
                    result.get().setDescription(qualificationStatus.getDescription());
                }

                qualificationStatus = result.get();
            }

        } else {
            qualificationStatus.setCreatedBy("");
            qualificationStatus.setActive(true);
        }

        return repository.save(qualificationStatus);
    }

    @Override
    public QualificationStatus findById(String id) {
        Optional<QualificationStatus> result = repository.findById(id);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Qualification Status Not Found");
        }

        return result.get();
    }

    @Override
    public void delete(QualificationStatus qualificationStatus) {
        QualificationStatus result = findById(qualificationStatus.getId());
        result.setActive(false);
        repository.save(result);
    }

    @Override
    public List<QualificationStatus> findAll() {
        List<QualificationStatus> result = repository.findAll();
        return result;
    }

}
