package com.clairvoyant.services.skillmatrix.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ManagerAssessment {
  private int skillLevel;
  private String  managerName;
}
