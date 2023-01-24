package com.clairvoyant.clarise.dto;

import lombok.Data;

import java.util.List;

@Data
public class AssessmentDto {

    private  String userId;
    private String comments;
    private List<AssessmentCategoryList> assessmentCategoryList;
}
