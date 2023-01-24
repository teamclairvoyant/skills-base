package com.clairvoyant.clarise.service.impl;

import com.clairvoyant.clarise.dto.QualificationDto;
import com.clairvoyant.clarise.model.Qualification;
import com.clairvoyant.clarise.repository.QualificationRepository;
import com.clairvoyant.clarise.service.QualificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QualificationServiceImpl implements QualificationService {

    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<QualificationDto> findAll() {
        return qualificationRepository.findAll().stream().map(qualification -> modelMapper.map(qualification, QualificationDto.class)).collect(Collectors.toList());
    }

    @Override
    public QualificationDto addOrUpdateQualification(QualificationDto qualificationDto) {
        Qualification qualification = null;
        if (StringUtils.hasText(qualificationDto.getId())) {
            Optional<Qualification> dbQualification = qualificationRepository.findById(qualificationDto.getId());
            if (dbQualification.isPresent()) {
                if (StringUtils.hasLength(qualificationDto.getName()))
                    dbQualification.get().setName(qualificationDto.getName());

                if (StringUtils.hasLength(qualificationDto.getDescription()))
                    dbQualification.get().setDescription(qualificationDto.getDescription());

                qualification = dbQualification.get();
            }
        }
        else {
            qualification = modelMapper.map(qualificationDto, Qualification.class);
            qualification.setActive(true);
        }

        if (Objects.nonNull(qualification)) {
            qualification = qualificationRepository.save(qualification);
        }

        return modelMapper.map(qualification, QualificationDto.class);
    }

    @Override
    public QualificationDto findQualification(String qualificationId) {
        return modelMapper.map(qualificationRepository.findById(qualificationId).get(), QualificationDto.class);
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
