package com.clairvoyant.services.skillmatrix.repository;

import com.clairvoyant.services.skillmatrix.model.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, String> {
}
