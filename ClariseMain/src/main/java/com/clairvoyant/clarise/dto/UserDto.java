package com.clairvoyant.clarise.dto;

import com.clairvoyant.clarise.model.Category;
import com.clairvoyant.clarise.model.Designation;
import com.clairvoyant.clarise.model.Role;
import com.clairvoyant.clarise.model.UserCategoryMapping;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Data
public class UserDto {
    private String id;

    private String name;

    private String emailAddress;

    private String grade;

    private String password;

    private String reportingManager;

    private boolean isActive;

//    private UserDesignationDto userDesignationDto;

    private List<String> userRoleIds;

    private List<String> userCategoryIds;
}
