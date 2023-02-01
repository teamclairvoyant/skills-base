package com.clairvoyant.services.skillmatrix.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {

   private String userId;

    private List<String> roleIds;

}
