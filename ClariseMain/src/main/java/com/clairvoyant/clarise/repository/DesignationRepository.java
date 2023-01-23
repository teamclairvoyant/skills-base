package com.clairvoyant.clarise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clairvoyant.clarise.model.Designation;

import java.util.List;
import java.util.Optional;

public interface DesignationRepository extends JpaRepository<Designation, String> {

    Optional<List<Designation>> findByIsActive(Boolean b);
    Optional<Designation> findByIdAndIsActive(String id,Boolean b);
}
