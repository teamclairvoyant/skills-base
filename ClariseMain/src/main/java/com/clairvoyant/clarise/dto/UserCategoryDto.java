package com.clairvoyant.clarise.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCategoryDto {

    private String userId;

    private List<String> categoryIds;

}
