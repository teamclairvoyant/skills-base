package com.clairvoyant.services.skillmatrix.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QualificationDto {

    private String id;
    private String name;
    private String description;
    private boolean isActive;


}
