package com.clairvoyant.services.skillmatrix.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SkillMatrixResponse {
    private List<SkillMatrixList> skillMatrixLists;
}
