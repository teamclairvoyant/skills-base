package com.clairvoyant.services.skillmatrix.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SkillCategoryMatrixList {
    private String skillCategoryId;
    private String skillName;
    private AveragedRatings averagedRatings;
    private SelfAssessment selfAssessment;
    private ManagerAssessment managerAssessment;
}
