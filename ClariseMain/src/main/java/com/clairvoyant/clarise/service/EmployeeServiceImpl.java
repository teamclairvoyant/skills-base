package com.clairvoyant.clarise.service;


import com.clairvoyant.clarise.model.Employee;
import com.clairvoyant.clarise.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee findByEmail(String email){
        return employeeRepository.findByEmail(email);
    }

    public void saveEmployee(Employee employee) { employeeRepository.save(employee); }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(String employeeId) {
        Optional<Employee> result = employeeRepository.findById(employeeId);

        Employee tempEmployee = null;

        if(result.isPresent()) {
            tempEmployee =result.get();
        }

        return tempEmployee;
    }

    @Override
    public void updateEmployee(String employeeId, Employee employeeDetails) {
       Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new RuntimeException("Employee not exist with empId: "+employeeId));
        //employee.setRole(employeeDetails.getRole());

        employeeRepository.save(employee);
    }

}