package com.clairvoyant.clarise.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillRatingInput {
    private String id;
    private String name;
    private String description;
    private Boolean isActive;
}
