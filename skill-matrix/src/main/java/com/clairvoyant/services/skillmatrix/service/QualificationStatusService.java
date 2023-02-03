package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.model.QualificationStatus;
import java.util.List;


public interface QualificationStatusService {

    QualificationStatus save(QualificationStatus qualificationStatus);

    QualificationStatus findById(String id);

    void delete(QualificationStatus qualificationStatus);

    List<QualificationStatus> findAll();
}
