package com.clairvoyant.clarise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkillCategorySelected {

    private String skillCategoryId;
    private String skillName;
    private String selectedSkillRatingId;
}
