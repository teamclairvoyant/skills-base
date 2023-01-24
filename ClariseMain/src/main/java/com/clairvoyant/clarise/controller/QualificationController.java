package com.clairvoyant.clarise.controller;

import com.clairvoyant.clarise.dto.QualificationDto;
import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.service.QualificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//qualification controller
@RestController
@RequestMapping("/v1/qualification")
@Validated
public class QualificationController {

    private static final Logger LOGGER = LogManager.getLogger(QualificationController.class);

    @Autowired
    private QualificationService qualificationService;

    //get all qualifications
    @GetMapping
    public List<QualificationDto> findAll(){

        return qualificationService.findAll();
    }

    //add or update qualification to qualification table
    @PutMapping
    public QualificationDto addOrUpdateQualification(@RequestBody QualificationDto qualificationDto) {

        LOGGER.info(qualificationDto);

        return qualificationService.addOrUpdateQualification(qualificationDto);
    }

    //get qualification
    @GetMapping("/{qualificationId}")
    public QualificationDto find(@PathVariable String qualificationId){

        return qualificationService.findQualification(qualificationId);
    }

    //delete qualification
    @DeleteMapping("/{qualificationId}")
    public Status deleteQualification(@PathVariable String qualificationId) {

        LOGGER.info("deleteQualification of qualification Id :" + qualificationId);

        qualificationService.deleteQualification(qualificationId);

        return Status.SUCCESS;
    }
}
