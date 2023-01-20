package com.clairvoyant.clarise.dto;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;

    private String name;

    private String emailAddress;

    private String grade;

    private String password;

    private String reportingManager;

    private boolean isActive;

    private String userDesignationId;

    private List<String> userRoleIds;

    private List<String> userCategoryIds;
}
