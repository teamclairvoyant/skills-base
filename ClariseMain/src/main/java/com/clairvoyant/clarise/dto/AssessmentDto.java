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
public class AssessmentDto {

    private  String userId;
    private String comments;
    private List<AssessmentCategoryList> assessmentCategoryList;
}
