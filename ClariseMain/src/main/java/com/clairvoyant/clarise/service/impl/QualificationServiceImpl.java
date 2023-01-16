package com.clairvoyant.clarise.service.impl;

import com.clairvoyant.clarise.model.Qualification;
import com.clairvoyant.clarise.repository.QualificationRepository;
import com.clairvoyant.clarise.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class QualificationServiceImpl implements QualificationService {

    @Autowired
    private QualificationRepository qualificationRepository;


    @Override
    public List<Qualification> findAll() {
        return qualificationRepository.findAll();
    }

    @Override
    public Qualification addOrUpdateQualification(Qualification qualification) {
        if (StringUtils.hasText(qualification.getId())) {
            Optional<Qualification> dbQualification = qualificationRepository.findById(qualification.getId());
            if (dbQualification.isPresent()) {
                if (StringUtils.hasLength(qualification.getName()))
                    dbQualification.get().setName(qualification.getName());

                if (StringUtils.hasLength(qualification.getDescription()))
                    dbQualification.get().setDescription(qualification.getDescription());

                qualification = dbQualification.get();
            }
        }
        else {
            qualification.setActive(true);
        }
        return qualificationRepository.save(qualification);
    }

    @Override
    public Qualification findQualification(String qualificationId) {
        return qualificationRepository.findById(qualificationId).get();
    }

    @Override
    public void deleteQualification(String qualificationId) {
        Optional<Qualification> dbQualification = qualificationRepository.findById(qualificationId);
        if (dbQualification.isPresent()) {
            Qualification qualification = dbQualification.get();
            qualification.setActive(false);
            qualificationRepository.save(qualification);
        }
    }
}
