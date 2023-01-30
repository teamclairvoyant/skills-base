package com.clairvoyant.clarise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignationDto {
    private String id;
    private String name;
    private String description;
    private Boolean isActive;
}
