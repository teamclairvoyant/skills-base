package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.dto.SkillDto;
import com.clairvoyant.services.skillmatrix.exceptions.DuplicateNameException;
import com.clairvoyant.services.skillmatrix.exceptions.ResourceNotFoundException;
import com.clairvoyant.services.skillmatrix.model.Skill;
import com.clairvoyant.services.skillmatrix.repository.SkillRepository;
import com.clairvoyant.services.skillmatrix.service.SkillService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        try {
            if (StringUtils.hasText(skillDto.getId())) {
                Optional<Skill> dbSkill = skillRepository.findById(skillDto.getId());
                if (dbSkill.isPresent()) {
                    if (StringUtils.hasLength(skillDto.getSkillName())) {
                        dbSkill.get().setSkillName(skillDto.getSkillName());
                    }

                    if (StringUtils.hasLength(skillDto.getDescription())) {
                        dbSkill.get().setDescription(skillDto.getDescription());
                    }

                    skill = dbSkill.get();
                } else {
                    throw new ResourceNotFoundException("Skill with id " + skillDto.getId() + " not found");
                }
            } else {
                skill = modelMapper.map(skillDto, Skill.class);
                skill.setActive(true);
            }

            if (Objects.nonNull(skill)) {
                skill = skillRepository.save(skill);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateNameException("Skill with name " + skillDto.getSkillName() + " already exists");
        }

        return modelMapper.map(skill, SkillDto.class);
    }

    @Override
    public SkillDto findSkill(String skillId) {
        Optional<Skill> dbSkill = skillRepository.findById(skillId);
        if (dbSkill.isEmpty()) {
            throw new ResourceNotFoundException("Skill with id " + skillId + " not found");
        }
        return modelMapper.map(dbSkill.get(), SkillDto.class);
    }

    @Override
    public void deleteSkill(String skillId) {
        Optional<Skill> dbSkill = skillRepository.findById(skillId);
        if (dbSkill.isPresent()) {
            Skill skill = dbSkill.get();
            skill.setActive(false);
            skillRepository.save(skill);
        } else {
            throw new ResourceNotFoundException("Skill with id " + skillId + " not found");
        }
    }

}