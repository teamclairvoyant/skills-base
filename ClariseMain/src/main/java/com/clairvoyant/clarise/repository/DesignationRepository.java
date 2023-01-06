package com.clairvoyant.clarise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clairvoyant.clarise.model.Designation;

public interface DesignationRepository extends JpaRepository<Designation, String> {

}
