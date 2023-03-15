package com.clairvoyant.services.skillmatrix.controller;

import com.clairvoyant.services.skillmatrix.dto.SkillDto;
import com.clairvoyant.services.skillmatrix.enums.Status;
import com.clairvoyant.services.skillmatrix.exceptions.DuplicateNameException;
import com.clairvoyant.services.skillmatrix.exceptions.ResourceNotFoundException;
import com.clairvoyant.services.skillmatrix.service.SkillService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SkillControllerTest {

    @InjectMocks
    SkillController skillController;

    @Mock
    SkillService skillService;

    @Test
    public void getSkills() {
        List<SkillDto> skillDtoList = new ArrayList<>();
        SkillDto skillDto = new SkillDto("any-id", "any-name", "any-desc", true);
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
    public void addOrUpdateSkillWithResourceNotFoundException() {
        SkillDto skillDto = new SkillDto();
        skillDto.setId("any-id");
        skillDto.setSkillName("any-name");
        skillDto.setDescription("any-desc");

        Mockito.when(skillService.addOrUpdateSkill(skillDto)).thenThrow(ResourceNotFoundException.class);
        Assert.assertThrows(ResourceNotFoundException.class, () -> {
            skillController.addOrUpdateSkill(skillDto);
        });
    }

    @Test
    public void addOrUpdateSkillWithDuplicateNameException() {
        SkillDto skillDto = new SkillDto();
        skillDto.setId("any-id");
        skillDto.setSkillName("any-name");
        skillDto.setDescription("any-desc");

        Mockito.when(skillService.addOrUpdateSkill(skillDto)).thenThrow(DuplicateNameException.class);
        Assert.assertThrows(DuplicateNameException.class, () -> {
            skillController.addOrUpdateSkill(skillDto);
        });
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
    public void findSkillByIdWithResourceNotFoundException() {
        Mockito.when(skillService.findSkill("any-id")).thenThrow(ResourceNotFoundException.class);
        Assert.assertThrows(ResourceNotFoundException.class, () -> {
            skillController.find("any-id");
        });
    }

    @Test
    public void deleteSkill() {
        Assert.assertEquals(Status.SUCCESS, skillController.deleteSkill("any-id"));
    }

    @Test
    public void deleteSkillWithResourceNotFoundException() {
        Mockito.doThrow(ResourceNotFoundException.class).when(skillService).deleteSkill("any-id");
        Assert.assertThrows(ResourceNotFoundException.class, () -> {
            skillController.deleteSkill("any-id");
        });
    }
}
