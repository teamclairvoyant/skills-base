package com.clairvoyant.clarise.input;

import lombok.Data;

@Data
public class UserInput {
    String name;
    String emailAddress;
    String grade;
    String password;
    String reportingManager;
    DesignationInput designation;
}
