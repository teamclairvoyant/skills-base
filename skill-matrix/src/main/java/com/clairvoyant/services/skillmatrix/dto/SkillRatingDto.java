package com.clairvoyant.services.skillmatrix.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillRatingDto {
   private String id;
   private String name;
   private String description ;
   private Boolean isActive;
}
