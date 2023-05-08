package com.clairvoyant.services.skillmatrix.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SkillMatrixList {
    private String categoryId;
    private String categoryName;
    private List<SkillCategoryMatrixList> skillCategoryMatrixList;
}
