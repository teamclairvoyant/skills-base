package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.model.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee findByEmail(String email);

    public void saveEmployee(Employee employee);

    public List<Employee> findAll();

    public Employee findById(String employeeId);

    public void updateEmployee(String employeeId, Employee employee);
}
