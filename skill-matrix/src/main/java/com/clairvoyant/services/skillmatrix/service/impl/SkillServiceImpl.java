package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.model.Skill;
import com.clairvoyant.services.skillmatrix.repository.SkillRepository;
import com.clairvoyant.services.skillmatrix.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;


    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill addOrUpdateSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public Skill findSkill(String skillId) {
        return skillRepository.findById(skillId).get();
    }

    @Override
    public void deleteSkill(String skillId) {
        Optional<Skill> optSkill = skillRepository.findById(skillId);
        if (optSkill.isPresent()) {
            Skill skill = optSkill.get();
            skill.setActive(false);
            skillRepository.save(skill);
        }
    }

}