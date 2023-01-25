package com.clairvoyant.clarise.controller;

import com.clairvoyant.clarise.dto.QualificationDto;
import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.service.QualificationService;
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
public class QualificationControllerTest {

    @InjectMocks
    QualificationController qualificationController;

    @Mock
    QualificationService qualificationService;

    @Test
    public void getQualifications() {
        List<QualificationDto> qualificationDtoList = new ArrayList<>();
        QualificationDto qualificationDto = new QualificationDto();
        qualificationDto.setId("any-id");
        qualificationDto.setName("any-name");
        qualificationDto.setDescription("any-desc");
        qualificationDto.setActive(true);
        qualificationDtoList.add(qualificationDto);
        Mockito.when(qualificationService.findAll()).thenReturn(qualificationDtoList);
        Assert.assertEquals(qualificationDtoList, qualificationController.findAll());
    }

    @Test
    public void getQualificationsEmpty() {
        Mockito.when(qualificationService.findAll()).thenReturn(new ArrayList());
        Assert.assertEquals(new ArrayList(), qualificationController.findAll());
    }

    @Test
    public void addQualification() {
        QualificationDto qualificationDto = new QualificationDto();
        qualificationDto.setName("any-name");
        qualificationDto.setDescription("any-desc");

        Mockito.when(qualificationService.addOrUpdateQualification(qualificationDto)).thenReturn(qualificationDto);
        Assert.assertEquals(qualificationDto, qualificationController.addOrUpdateQualification(qualificationDto));
    }

    @Test
    public void updateQualification() {
        QualificationDto qualificationDto = new QualificationDto();
        qualificationDto.setId("any-id");
        qualificationDto.setName("any-name");
        qualificationDto.setDescription("any-desc");

        Mockito.when(qualificationService.addOrUpdateQualification(qualificationDto)).thenReturn(qualificationDto);
        Assert.assertEquals(qualificationDto, qualificationController.addOrUpdateQualification(qualificationDto));
    }

    @Test
    public void findQualificationById() {
        QualificationDto qualificationDto = new QualificationDto();
        qualificationDto.setId("any-id");
        qualificationDto.setName("any-name");
        qualificationDto.setDescription("any-desc");
        qualificationDto.setActive(true);

        Mockito.when(qualificationService.findQualification("any-id")).thenReturn(qualificationDto);
        Assert.assertEquals(qualificationDto, qualificationController.find("any-id"));
    }

    @Test
    public void deleteQualification() {
        Assert.assertEquals(Status.SUCCESS, qualificationController.deleteQualification("any-id"));
    }
}
