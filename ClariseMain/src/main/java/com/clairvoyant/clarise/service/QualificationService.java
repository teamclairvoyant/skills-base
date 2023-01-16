package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.model.Qualification;

import java.util.List;

public interface QualificationService {

    public List<Qualification> findAll();

    public Qualification addOrUpdateQualification(Qualification qualification);

    public Qualification findQualification(String qualificationId);

    public void deleteQualification(String qualificationId);
}
