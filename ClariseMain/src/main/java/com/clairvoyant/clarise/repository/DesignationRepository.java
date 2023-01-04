package com.clairvoyant.clarise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clairvoyant.clarise.entities.Designation;

public interface DesignationRepository extends JpaRepository<Designation, String> {

}
