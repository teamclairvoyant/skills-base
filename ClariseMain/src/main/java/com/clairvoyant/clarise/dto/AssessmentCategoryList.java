package com.clairvoyant.clarise.dto;

import lombok.Data;

import java.util.List;

@Data
public class AssessmentCategoryList {
    private String categoryId;
    private String categoryName;
    private List<SkillCategorySelected> skillCategorySelected;

}
