package com.clairvoyant.services.skillmatrix.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DesignationDto {
    private String id;
    private String name;
    private String description;
    private Boolean isActive;
}
