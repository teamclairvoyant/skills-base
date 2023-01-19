package com.clairvoyant.clarise.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DesignationInput {

    private String name;
    private String description;
    private Boolean isActive;
}
