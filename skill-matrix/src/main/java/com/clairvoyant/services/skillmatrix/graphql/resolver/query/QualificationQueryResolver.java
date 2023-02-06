package com.clairvoyant.services.skillmatrix.graphql.resolver.query;

import com.clairvoyant.services.skillmatrix.dto.QualificationDto;
import com.clairvoyant.services.skillmatrix.service.QualificationService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
