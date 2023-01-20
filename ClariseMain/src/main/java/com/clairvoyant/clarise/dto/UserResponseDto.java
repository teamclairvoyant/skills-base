package com.clairvoyant.clarise.dto;

import com.clairvoyant.clarise.model.Category;
import com.clairvoyant.clarise.model.Designation;
import com.clairvoyant.clarise.model.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String id;
    private String name;
    private String emailAddress;
    private String grade;
    private String password;
    private String reportingManager;
    private boolean isActive;
    private Designation designation;
    private List<Role> userRoles;
    private List<Category> userCategories;
}
