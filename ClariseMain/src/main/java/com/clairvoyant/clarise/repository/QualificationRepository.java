package com.clairvoyant.clarise.repository;

import com.clairvoyant.clarise.model.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, String> {
}
