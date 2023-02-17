package com.clairvoyant.services.skillmatrix.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SkillDto {

    private String id;
    private String skillName;
    private String description;
    private boolean isActive;

}
