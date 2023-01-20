package com.clairvoyant.clarise.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {

   private String userId;

    private List<String> roleIds;

}
