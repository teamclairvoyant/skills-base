package com.clairvoyant.services.skillmatrix.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SkillMatrixResponse {
    private List<SkillMatrixList> skillMatrixLists;
}
