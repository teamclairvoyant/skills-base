package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.model.Skill;
import java.util.List;

public interface SkillService {

    List<Skill> findAll();

    Skill addOrUpdateSkill(Skill skill);

    Skill findSkill(String skillId);

    void deleteSkill(String skillId);

}













