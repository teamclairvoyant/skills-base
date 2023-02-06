package com.clairvoyant.services.skillmatrix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;

    @NotBlank(message = "Name is Required")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]*",message = "Enter valid name")
    private String name;

    @NotBlank(message = "Email Address is Required")
    @Email(message = "Email should be valid")
    private String emailAddress;

    private String grade;
    private String password;
    private String reportingManager;
    private boolean isActive;

    @NotBlank
    private String userDesignationId;

    @NotEmpty
    private List<String> userRoleIds;

    @NotEmpty
    private List<String> userCategoryIds;
}
