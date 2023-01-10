package com.clairvoyant.clarise.input;

import lombok.Data;

@Data
public class SkillRatingInput {
    String id;
    String name;
    String description;
    Boolean isActive;
}
