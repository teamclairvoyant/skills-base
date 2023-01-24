package com.clairvoyant.clarise.dto;

import com.clairvoyant.clarise.model.SkillCategory;
import com.clairvoyant.clarise.model.SkillsRating;
import lombok.Data;

import java.util.List;

@Data
public class SkillCategoryResponse {

    private List<CategoryList> categoryList;

}
