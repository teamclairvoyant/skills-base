package com.clairvoyant.services.skillmatrix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QualificationDto {

    private String id;
    private String name;
    private String description;
    private boolean isActive;


}
