package com.clairvoyant.services.skillmatrix.repository;

import com.clairvoyant.services.skillmatrix.model.Designation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignationRepository extends JpaRepository<Designation, String> {

    Optional<List<Designation>> findByIsActive(Boolean b);
    Optional<Designation> findByIdAndIsActive(String id,Boolean b);
}
