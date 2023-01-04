package com.clairvoyant.clarise.repository;

import com.clairvoyant.clarise.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findByEmail(String email);



}
