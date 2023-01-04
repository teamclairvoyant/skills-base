package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.model.Skill;

import java.util.List;

public interface SkillService {

    public List<Skill> findAll();

    public Skill addOrUpdateSkill(Skill skill);

    public Skill findSkill(String skillId);

    public void deleteSkill(String skillId);

}













