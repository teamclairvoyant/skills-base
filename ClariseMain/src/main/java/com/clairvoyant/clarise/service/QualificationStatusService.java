package com.clairvoyant.clarise.service;

import java.util.List;

import com.clairvoyant.clarise.entities.QualificationStatus;


public interface QualificationStatusService {

	public QualificationStatus save(QualificationStatus qualificationStatus);

	public QualificationStatus findById(String id);

	public void delete(QualificationStatus qualificationStatus);

	public List<QualificationStatus> findAll();
}
