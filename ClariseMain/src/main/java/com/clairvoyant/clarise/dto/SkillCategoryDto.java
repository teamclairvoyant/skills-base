package com.clairvoyant.clarise.dto;

import lombok.Data;

import java.util.List;

@Data
public class SkillCategoryDto {

    private String categoryId;

    private List<String> skillIds;
}
