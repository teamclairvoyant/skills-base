package com.clairvoyant.clarise.service.impl;

import com.clairvoyant.clarise.dto.SkillDto;
import com.clairvoyant.clarise.model.Skill;
import com.clairvoyant.clarise.repository.SkillRepository;
import com.clairvoyant.clarise.service.SkillService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<SkillDto> findAll() {
        return skillRepository.findAll().stream().map(skill -> modelMapper.map(skill, SkillDto.class)).collect(Collectors.toList());
    }

    @Override
    public SkillDto addOrUpdateSkill(SkillDto skillDto) {
        Skill skill = null;
        if (StringUtils.hasText(skillDto.getId())) {
            Optional<Skill> dbSkill = skillRepository.findById(skillDto.getId());
            if (dbSkill.isPresent()) {
                if (StringUtils.hasLength(skillDto.getSkillName()))
                    dbSkill.get().setSkillName(skillDto.getSkillName());

                if (StringUtils.hasLength(skillDto.getDescription()))
                    dbSkill.get().setDescription(skillDto.getDescription());

                skill = dbSkill.get();
            }
        }
        else {
            skill = modelMapper.map(skillDto, Skill.class);
            skill.setActive(true);
        }

        if (Objects.nonNull(skill)) {
            skill = skillRepository.save(skill);
        }

        return modelMapper.map(skill, SkillDto.class);
    }

    @Override
    public SkillDto findSkill(String skillId) {
        return modelMapper.map(skillRepository.findById(skillId).get(), SkillDto.class);
    }

    @Override
    public void deleteSkill(String skillId) {
        Optional<Skill> dbSkill = skillRepository.findById(skillId);
        if (dbSkill.isPresent()) {
            Skill skill = dbSkill.get();
            skill.setActive(false);
            skillRepository.save(skill);
        }
    }

}