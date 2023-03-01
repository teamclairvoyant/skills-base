package com.clairvoyant.services.skillmatrix.controller;

import com.clairvoyant.services.skillmatrix.dto.QualificationDto;
import com.clairvoyant.services.skillmatrix.enums.Status;
import com.clairvoyant.services.skillmatrix.exceptions.DuplicateNameException;
import com.clairvoyant.services.skillmatrix.exceptions.ResourceNotFoundException;
import com.clairvoyant.services.skillmatrix.service.QualificationService;
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
public class QualificationControllerTest {

    @InjectMocks
    QualificationController qualificationController;

    @Mock
    QualificationService qualificationService;

    @Test
    public void getQualifications() {
        List<QualificationDto> qualificationDtoList = new ArrayList<>();
        QualificationDto qualificationDto = new QualificationDto("any-id", "any-name", "any-desc", true);
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
    public void addOrUpdateQualificationWithResourceNotFoundException() {
        QualificationDto qualificationDto = new QualificationDto();
        qualificationDto.setId("any-id");
        qualificationDto.setName("any-name");
        qualificationDto.setDescription("any-desc");

        Mockito.when(qualificationService.addOrUpdateQualification(qualificationDto)).thenThrow(ResourceNotFoundException.class);
        Assert.assertThrows(ResourceNotFoundException.class, () -> {
            qualificationController.addOrUpdateQualification(qualificationDto);
        });
    }

    @Test
    public void addOrUpdateQualificationWithDuplicateNameException() {
        QualificationDto qualificationDto = new QualificationDto();
        qualificationDto.setId("any-id");
        qualificationDto.setName("any-name");
        qualificationDto.setDescription("any-desc");

        Mockito.when(qualificationService.addOrUpdateQualification(qualificationDto)).thenThrow(DuplicateNameException.class);
        Assert.assertThrows(DuplicateNameException.class, () -> {
            qualificationController.addOrUpdateQualification(qualificationDto);
        });
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
    public void findQualificationByIdWithResourceNotFoundException() {
        Mockito.when(qualificationService.findQualification("any-id")).thenThrow(ResourceNotFoundException.class);
        Assert.assertThrows(ResourceNotFoundException.class, () -> {
            qualificationController.find("any-id");
        });
    }

    @Test
    public void deleteQualification() {
        Assert.assertEquals(Status.SUCCESS, qualificationController.deleteQualification("any-id"));
    }

    @Test
    public void deleteQualificationWithResourceNotFoundException() {
        Mockito.doThrow(ResourceNotFoundException.class).when(qualificationService).deleteQualification("any-id");
        Assert.assertThrows(ResourceNotFoundException.class, () -> {
            qualificationController.deleteQualification("any-id");
        });
    }
}
