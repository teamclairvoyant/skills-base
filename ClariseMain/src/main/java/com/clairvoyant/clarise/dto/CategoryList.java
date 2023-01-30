package com.clairvoyant.clarise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryList {
    private String categoryId;
    private String categoryName;
    private List<SkillCategoryList> skillCategoryList;

}
