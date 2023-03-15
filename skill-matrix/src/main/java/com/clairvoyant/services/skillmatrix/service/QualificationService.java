package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.dto.QualificationDto;
import java.util.List;

public interface QualificationService {

    public List<QualificationDto> findAll();

    public QualificationDto addOrUpdateQualification(QualificationDto qualificationDto);

    public QualificationDto findQualification(String qualificationId);

    public void deleteQualification(String qualificationId);
}
