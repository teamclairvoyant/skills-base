package com.clairvoyant.services.skillmatrix.dto;

import lombok.Data;

import java.util.List;

@Data
public class SkillCategoryDto {

    private String categoryId;

    private List<String> skillIds;
}
