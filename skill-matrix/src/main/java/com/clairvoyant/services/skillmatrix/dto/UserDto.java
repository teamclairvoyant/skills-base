package com.clairvoyant.services.skillmatrix.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", emailAddress='" + "********" + '\'' +
                ", grade='" + grade + '\'' +
                ", password='" + "*********" + '\'' +
                ", reportingManager='" + reportingManager + '\'' +
                ", isActive=" + isActive +
                ", userDesignationId='" + userDesignationId + '\'' +
                ", userRoleIds=" + userRoleIds +
                ", userCategoryIds=" + userCategoryIds +
                '}';
    }
}
