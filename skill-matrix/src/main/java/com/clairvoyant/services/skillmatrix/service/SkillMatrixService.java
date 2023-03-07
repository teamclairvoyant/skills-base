package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.dto.SkillMatrixResponse;
import java.util.List;

public interface SkillMatrixService {

    SkillMatrixResponse getSkills(String userId, List<String> categoryIds);
}
