package com.clairvoyant.services.skillmatrix.dto;

import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    @Pattern(regexp = "([A-Za-z0-9\\-]+)")
    private String id;

    @NotNull(message = "Name field cannot be null")
    @Pattern(regexp = "([A-Za-z\\ ]+)",message = "Name should only contains characters")
    private String name;

    @NotNull(message = "Email field can not be null")
    @NotBlank(message = "Email field can not be blank")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String emailAddress;

    private String grade;

    @NotNull(message = "Password field cannot be null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^=])(?=\\S+$).{8,20}$", message = "Password Should have at least :"
            + "eight characters "
            + ", one uppercase letter "
            + ", one lowercase letter "
            + ", one number and "
            + ", one special character")
    private String password;

    @NotNull(message = "Reporting Manager field can not be null")
    @NotBlank(message = "Reporting Manager field can not be blank")
    private String reportingManager;

    private boolean isActive;

    @NotNull(message = "User Designation field can not be null")
    @NotBlank(message = "User Designation field can not be blank")
    private String userDesignationId;

    @NotNull(message = "User Role field Can not be null")
    @NotEmpty(message = "User Roles field can not be blank")
    private List<String> userRoleIds;

    @NotNull(message = "User Category field can not be null")
    @NotEmpty(message = "User Category field can not be blank")
    private List<String> userCategoryIds;
}
