package com.clairvoyant.services.skillmatrix.controller;

import com.clairvoyant.services.skillmatrix.dto.SkillDto;
import com.clairvoyant.services.skillmatrix.enums.Status;
import com.clairvoyant.services.skillmatrix.service.SkillService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SkillControllerTest {

    @InjectMocks
    SkillController skillController;

    @Mock
    SkillService skillService;

    @Test
    public void getSkills() {
        List<SkillDto> skillDtoList = new ArrayList<>();
        SkillDto skillDto = new SkillDto();
        skillDto.setId("any-id");
        skillDto.setSkillName("any-name");
        skillDto.setDescription("any-desc");
        skillDto.setActive(true);
        skillDtoList.add(skillDto);
        Mockito.when(skillService.findAll()).thenReturn(skillDtoList);
        Assert.assertEquals(skillDtoList, skillController.findAll());
    }

    @Test
    public void getSkillsEmpty() {
        Mockito.when(skillService.findAll()).thenReturn(new ArrayList());
        Assert.assertEquals(new ArrayList(), skillController.findAll());
    }

    @Test
    public void addSkill() {
        SkillDto skillDto = new SkillDto();
        skillDto.setSkillName("any-name");
        skillDto.setDescription("any-desc");

        Mockito.when(skillService.addOrUpdateSkill(skillDto)).thenReturn(skillDto);
        Assert.assertEquals(skillDto, skillController.addOrUpdateSkill(skillDto));
    }

    @Test
    public void updateSkill() {
        SkillDto skillDto = new SkillDto();
        skillDto.setId("any-id");
        skillDto.setSkillName("any-name");
        skillDto.setDescription("any-desc");

        Mockito.when(skillService.addOrUpdateSkill(skillDto)).thenReturn(skillDto);
        Assert.assertEquals(skillDto, skillController.addOrUpdateSkill(skillDto));
    }

    @Test
    public void findSkillById() {
        SkillDto skillDto = new SkillDto();
        skillDto.setId("any-id");
        skillDto.setSkillName("any-name");
        skillDto.setDescription("any-desc");
        skillDto.setActive(true);

        Mockito.when(skillService.findSkill("any-id")).thenReturn(skillDto);
        Assert.assertEquals(skillDto, skillController.find("any-id"));
    }

    @Test
    public void deleteSkill() {
        Assert.assertEquals(Status.SUCCESS, skillController.deleteSkill("any-id"));
    }
}
