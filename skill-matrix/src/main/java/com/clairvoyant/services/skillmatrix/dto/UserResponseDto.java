package com.clairvoyant.services.skillmatrix.dto;

import com.clairvoyant.services.skillmatrix.model.Category;
import com.clairvoyant.services.skillmatrix.model.Designation;
import com.clairvoyant.services.skillmatrix.model.Role;
import com.clairvoyant.services.skillmatrix.model.User;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String id;
    private String name;
    private String emailAddress;
    private String grade;
    private User reportingManager;
    private boolean isActive;
    private Designation designation;
    private List<Role> userRoles;
    private List<Category> userCategories;

    @Override
    public String toString() {
        return "UserResponseDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", emailAddress='" + "**********" + '\'' +
                ", grade='" + grade + '\'' +
                ", reportingManager=" + reportingManager +
                ", isActive=" + isActive +
                ", designation=" + designation +
                ", userRoles=" + userRoles +
                ", userCategories=" + userCategories +
                '}';
    }
}
