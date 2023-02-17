package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.dto.SkillDto;
import com.clairvoyant.services.skillmatrix.exceptions.ResourceNotFoundException;
import com.clairvoyant.services.skillmatrix.model.Skill;
import com.clairvoyant.services.skillmatrix.repository.SkillRepository;
import com.clairvoyant.services.skillmatrix.service.SkillService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;


    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public Page<Skill> findAll(int page, int size) {
        var pageRequest = PageRequest.of(page, size);
        return skillRepository.findAll(pageRequest);
    }

    @Override
    public Skill addOrUpdateSkill(SkillDto skillDto) {
        var skill = new Skill();
        BeanUtils.copyProperties(skillDto,skill);
        return skillRepository.save(skill);
    }

    @Override
    public Skill findSkill(String skillId) {
        Optional<Skill> optSkill = skillRepository.findById(skillId);
        Skill skill;
        if (optSkill.isPresent()) {
            skill = optSkill.get();
        } else {
            throw new ResourceNotFoundException("Skill does not exist with skill id : " + skillId);
        }
        return skill;
    }

    @Override
    public void deleteSkill(String skillId) {
        Optional<Skill> optSkill = skillRepository.findById(skillId);
        if (optSkill.isPresent()) {
            var skill = optSkill.get();
            skill.setActive(false);
            skillRepository.save(skill);
        }
    }

}