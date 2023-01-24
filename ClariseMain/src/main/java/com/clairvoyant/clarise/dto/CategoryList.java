package com.clairvoyant.clarise.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryList {
    private String categoryId;
    private String categoryName;
    private List<SkillCategoryList> skillCategoryList;

}
