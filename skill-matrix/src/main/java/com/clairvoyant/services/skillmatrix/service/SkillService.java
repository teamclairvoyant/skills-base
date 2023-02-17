package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.dto.SkillDto;
import com.clairvoyant.services.skillmatrix.model.Skill;
import java.util.List;
import org.springframework.data.domain.Page;

public interface SkillService {

    List<Skill> findAll();

    Page<Skill> findAll(int page, int size);

    Skill addOrUpdateSkill(SkillDto skillDto);

    Skill findSkill(String skillId);

    void deleteSkill(String skillId);

}













