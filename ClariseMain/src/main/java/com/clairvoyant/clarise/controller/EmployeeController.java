package com.clairvoyant.clarise.controller;

import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.model.Employee;
import com.clairvoyant.clarise.repository.EmployeeRepository;
import com.clairvoyant.clarise.service.MailService;
import com.clairvoyant.clarise.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;

import java.util.List;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MailService mailService;

    private static final Logger LOGGER = LogManager.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
       this.employeeService = employeeService;
    }

    //get all employees
    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{employeeId}")
    public Employee findById(@PathVariable String employeeId){
        return employeeService.findById(employeeId);
    }


    //update Employee controller
    @PutMapping("/{employeeId}")
    public Status updateEmployee(@PathVariable String employeeId, @RequestBody Employee employeeDetails){

        employeeService.updateEmployee(employeeId, employeeDetails);

        return Status.SUCCESS;
    }
    /*@PutMapping("/update")
    public User updateUser(@RequestBody User user){
        userService.saveUser(user);
        return user;
    }*/


    //endpoint to send invitation link
    @PostMapping("/invitation")
    public Status sendEmail(@RequestBody Employee employee) throws MessagingException {

        mailService.sendEmail(employee);
        return Status.MESSAGE_SENT;
    }
}
