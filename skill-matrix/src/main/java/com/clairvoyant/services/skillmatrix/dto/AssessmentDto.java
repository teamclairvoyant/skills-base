package com.clairvoyant.services.skillmatrix.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssessmentDto {

    private  String userId;
    private String comments;
    private List<AssessmentCategoryList> assessmentCategoryList;
}
