package com.clairvoyant.services.skillmatrix.dto;

import java.util.List;
import lombok.Data;

@Data
public class SkillCategoryDto {

    private String categoryId;

    private List<String> skillIds;
}
