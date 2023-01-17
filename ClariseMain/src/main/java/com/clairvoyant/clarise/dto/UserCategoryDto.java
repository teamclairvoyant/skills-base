package com.clairvoyant.clarise.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserCategoryDto {

    private String userId;

    private List<String> categoryIds;

}
