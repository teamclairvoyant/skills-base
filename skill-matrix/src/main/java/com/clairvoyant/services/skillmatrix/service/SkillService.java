package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.dto.SkillDto;
import java.util.List;

public interface SkillService {

    List<SkillDto> findAll();

    SkillDto addOrUpdateSkill(SkillDto skillDto);

    SkillDto findSkill(String skillId);

    void deleteSkill(String skillId);

}













