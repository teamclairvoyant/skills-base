package com.clairvoyant.services.skillmatrix.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CategoryList {
    private String categoryId;
    private String categoryName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SkillCategoryList> skillCategoryList;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SkillCategorySelected> skillCategorySelected;
}
