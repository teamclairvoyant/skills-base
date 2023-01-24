package com.clairvoyant.clarise.graphql.resolver.query;

import com.clairvoyant.clarise.dto.QualificationDto;
import com.clairvoyant.clarise.service.QualificationService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class QualificationQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private QualificationService qualificationService;

    public List<QualificationDto> getQualifications() {
        return qualificationService.findAll();
    }

    public QualificationDto getQualificationById(String qualificationId) {
        return qualificationService.findQualification(qualificationId);
    }
}
