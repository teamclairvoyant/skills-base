package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.dto.SkillDto;
import java.util.List;

public interface SkillService {

   public List<SkillDto> findAll();

   public SkillDto addOrUpdateSkill(SkillDto skillDto);

   public SkillDto findSkill(String skillId);

   public void deleteSkill(String skillId);

}













