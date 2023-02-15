package com.clairvoyant.services.skillmatrix.dto;

import com.clairvoyant.services.skillmatrix.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String name;
    private String emailAddress;
    private User reportingManager;
    private String grade;
    private String token;

    @Override
    public String toString() {
        return "LoginResponseDto{"
                + "name='" + name + '\''
                + ", emailAddress='" + "********" + '\''
                + ", reportingManager=" + reportingManager
                + ", grade='" + grade + '\''
                + ", token='" + token + '\''
                + '}';
    }
}
