package com.clairvoyant.clarise.dto;

import com.clairvoyant.clarise.model.Category;
import com.clairvoyant.clarise.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {

    private String id;
    private String name;
    private String emailAddress;
    private String grade;
    private String password;
    private String reportingManager;
    private boolean isActive;
//    private UserDesignationDto userDesignationDto;
    private List<Role> userRoles;
    private List<Category> userCategories;
}
