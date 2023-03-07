package com.clairvoyant.services.skillmatrix.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SkillMatrixList {
    private String categoryId;
    private String categoryName;
    private List<SkillCategoryMatrixList> skillCategoryMatrixList;
}
