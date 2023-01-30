package com.clairvoyant.clarise.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QualificationDto {

    private String id;
    private String name;
    private String description;
    private boolean isActive;
}
