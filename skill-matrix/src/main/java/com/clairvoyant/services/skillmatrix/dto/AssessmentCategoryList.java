package com.clairvoyant.services.skillmatrix.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssessmentCategoryList {
    private String categoryId;
    private String categoryName;
    private List<SkillCategorySelected> skillCategorySelected;

}
